import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.Hashtable;
import java.util.Vector;
/*Lprop Class is used for Establishing LDAP bind and performing LDAP operations. It is master 
 * Class for Search LDAP and Test LDAP operations
 */
public class Lprop {
	public DirContext getLDAPconn() throws Exception{
		Hashtable env = new Hashtable();
		DirContext ctx = null;
        env.put(DirContext.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(DirContext.PROVIDER_URL, "ldap://ldap.itd.umich.edu");
        ctx = new InitialDirContext(env);
        return ctx;
	}
	public void doDnTest(String dn) throws Exception{
		Context ctx=  getLDAPconn();
		try{
		Object vec =ctx.lookup(dn);
		System.out.println("Object returned for DN" + dn +"is instance of vector =" + (vec instanceof Vector));
		}catch(NameNotFoundException e){
			System.out.println("Something Wrong in DN"+e);
		}
		ctx.close();
	}
}
