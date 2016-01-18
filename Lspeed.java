import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.Hashtable;
/**
 * Input: LDAP Filter is hard coded as of now
 * Output: LDAP Search result for particular User
 * Dependencies*-Public Ldap server
 */
public class Lspeed {
    String dn,lserver;
    //Constructor initializes default values
	Lspeed(){
		dn="uid=jwoodnh,ou=People,dc=umich,dc=edu";
        lserver="ldap://ldap.itd.umich.edu";     
	}
	Lspeed(String userdn,String userlserver){
		dn=userdn;
		lserver=userlserver;		
	}
	
	public static void main(String[] args) {
        
        NamingEnumeration results = null;
        // Hard Coded information
        
        Lspeed ldpobj=new Lspeed();
        
        try {
        	//proobj of Lprop class will be used for establishing LDAP interaction and Test 
        	Lprop proobj=new Lprop();
        	//Creating a Context object for getting LDAP Connection values
        	Context ctx=null;
            ctx = proobj.getLDAPconn(ldpobj.lserver);
            String filter=Lprop.setFilter("(&(cn=Jame*) (sn=Woodw*))");
            proobj.doDnTest(ldpobj.dn,ldpobj.lserver);
            results=proobj.getLdata(ctx, filter);
            while (results.hasMore()) {
                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes = searchResult.getAttributes();
                Attribute attr = attributes.get("cn");
                String cn = (String) attr.get();
                System.out.println(" Person Common Name = " + attributes.get("displayName"));
                
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
          if (results != null) {
          try {
                    results.close();
               } catch (Exception e) {
                 System.out.println("Closure of Result");
               }
            }
           
            }
        }
    }
