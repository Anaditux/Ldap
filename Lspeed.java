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

    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        @SuppressWarnings("rawtypes")
        NamingEnumeration results = null;
        // Hard Coded information
        String dn="uid=jwoodnh,ou=People,dc=umich,dc=edu";
        String lserver="ldap://ldap.itd.umich.edu";
        try {
        	//proobj of Lprop class will be used for establishing LDAP interaction and Test 
        	Lprop proobj=new Lprop();
        	//Creating a Context object for getting LDAP Connection values
        	Context ctx=null;
            ctx = proobj.getLDAPconn(lserver);      
            proobj.doDnTest(dn,lserver);
            
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ((DirContext) ctx).search("", "(&(cn=Jame*) (sn=Woodw*))", controls);
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
                }
            }
           
            }
        }
    }
