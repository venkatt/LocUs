import java.net.MalformedURLException;
import java.util.Set;

import de.uniba.wiai.lspi.chord.data.URL;
import de.uniba.wiai.lspi.chord.service.Chord;
import de.uniba.wiai.lspi.chord.service.ServiceException;


public class chordDHT implements IDHT {
	
	private static URL bootstrap_url = null ;
    private static URL local_url = null ; 
	private Chord chord_instance = null ; 
	
	public chordDHT() { 
		de.uniba.wiai.lspi.chord.service.PropertiesLoader.loadPropertyFile();	  	
	}
	
	
	/* This function will get the public bootstrap URL 
	 * Either use DNS or some other mechanism to get it. 
	 * For now implementing it using the local IP address
	 */
	 public static void set_bootstrap_url () { 

  	  if (bootstrap_url != null ) 
  		  return ; 
  	  
  	  String protocol = URL.KNOWN_PROTOCOLS.get(URL.SOCKET_PROTOCOL) ;
  	  try {
      	  bootstrap_url = new URL(protocol + "://localhost:8080/ ") ;
      	  }	catch	( MalformedURLException	e ) { 
      		  /* what should we do in this case ? */
      		  //throw new RuntimeException(e);
      	  }
    }
	
	public static void set_local_url() { 
	  
	  if ( local_url != null )
		  	return ; 
	    
	  String protocol = URL.KNOWN_PROTOCOLS.get(URL.SOCKET_PROTOCOL) ;
  	  	  
  	  /* Here instead of using local host .. try to get the public ip address 
  	   * of the host. Also if there are two chords in the system, then we might 
  	   * want to change the port. For now fixing the port that is used to connect 
  	   * to to the CHord network.
  	   */
  	  try {
  	  local_url = new URL( protocol + "://localhost:8082/ "); 
  	  }	catch	( MalformedURLException	e ) { throw new RuntimeException(e);  	    
  	  }
	}
	 
	 
	public Result join() {
  	  
  	  set_bootstrap_url();
  	  set_local_url();
  	  chord_instance = new de.uniba.wiai.lspi.chord.service.impl.ChordImpl( );  
  	
  	  if ((local_url == null) || (bootstrap_url == null))
  		  return new Result(false,-1);
  	  
  	  try {
  		  chord_instance.join( local_url,bootstrap_url) ;
  		  } catch (ServiceException e) { 
  			  e.printStackTrace(); 
  			  return new Result(false,-1);
  		  }		
  	  return Result.Success;
	}

	
	public Result create() {
	   
  	  /* the first guy that has created the Chord will be used as a bootstrap 
  	   * server for this rough implementation 
  	   */
  	  set_bootstrap_url();
  	  /* Getting an error if we use this chord instance to put and get some elements 
  	   * into the chord. So locally using this instance to just create the chord. and 
  	   * again join function has to be called to return the global instance that has to 
  	   * be used to put , delete and retreive elemets.
  	   */
  	  Chord lchord = new de.uniba.wiai.lspi.chord.service.impl.ChordImpl( );  
  	
  	  try {
  		 lchord.create (bootstrap_url) ;
  	   } catch (ServiceException e) { 
  		   //throw new RuntimeException("Could not create DHT!",e); 
  		   return new Result(false, -1);
  	   }

 	  return Result.Success;
	}

	
	public Result put(User user) {

		/* chord is not instantiated properly */
		if ((this.chord_instance == null) || (user == null) ) 
			return new Result(false,-1);

		
	    String tilenum = user.getTileNumber(); 
	    if ( tilenum == null) 
	    	return new Result(false,-1);
	    
	    
  	    /* Create a key based on the tile number */
        TileKey tk = new TileKey(tilenum);  
        try { 
      	  chord_instance.insert(tk,user);
        } catch (ServiceException e) { 
      	  //throw new RuntimeException("Can not insert the data into DHT!",e); 
          return new Result(false,-1);	
        }
        return Result.Success ; 
	}

	public Set<User> getUsersByKey(User user) {

		Set s = null ; 
    	try {
    		/* For now retrieving only the users from a given tile number. But 
    		 * we should call some tile api to get the surrounding tile numbers 
    		 * and then retrieve all the users from these tile numbers.
    		 */
    		s = chord_instance.retrieve(new TileKey(user.getTileNumber())) ;
    	} catch (ServiceException e) {
			e.printStackTrace();
			//throw new RuntimeException("Error occured in retreiving the Data",e);
			return null ; 
		} 
    	return s;     	  
	}
	
	public Result delete(User user) {

  	  try {
			chord_instance.remove(new TileKey(user.getTileNumber()), user);
		} catch (ServiceException e) {
			//e.printStackTrace();
			//throw new RuntimeException("Error occurred in removing the Data",e);			
			return new Result(false,-1);
		} 
		
		return Result.Success ; 
	}

	public void leave() { 
		
	}
	
}
