/**
 * Copyright(c) 2001 iSavvix Corporation (http://www.isavvix.com/)
 *
 *                        All rights reserved
 *
 * Permission to use, copy, modify and distribute this material for
 * any purpose and without fee is hereby granted, provided that the
 * above copyright notice and this permission notice appear in all
 * copies, and that the name of iSavvix Corporation not be used in
 * advertising or publicity pertaining to this material without the
 * specific, prior written permission of an authorized representative of
 * iSavvix Corporation.
 *
 * ISAVVIX CORPORATION MAKES NO REPRESENTATIONS AND EXTENDS NO WARRANTIES,
 * EXPRESS OR IMPLIED, WITH RESPECT TO THE SOFTWARE, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR ANY PARTICULAR PURPOSE, AND THE WARRANTY AGAINST
 * INFRINGEMENT OF PATENTS OR OTHER INTELLECTUAL PROPERTY RIGHTS.  THE
 * SOFTWARE IS PROVIDED "AS IS", AND IN NO EVENT SHALL ISAVVIX CORPORATION OR
 * ANY OF ITS AFFILIATES BE LIABLE FOR ANY DAMAGES, INCLUDING ANY
 * LOST PROFITS OR OTHER INCIDENTAL OR CONSEQUENTIAL DAMAGES RELATING
 * TO THE SOFTWARE.
 *
 */

package db;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

/**
 * Manages a java.sql.Connection pool.
 *
 * @author  Anil Hemrajani
 */
public class DBConnectionMgr {

    //
	//private MemberDBMgr mem =null;
    private Vector connections = new Vector(10);
    /*   MySQL */
    
    

    private String _driver,_url,_user,_password;
    /*
	private String _driver = "org.gjt.mm.mysql.Driver",
    _url = "jdbc:mysql://127.0.0.1:3306/mydb?useUnicode=true&characterEncoding=UTF-8",
    _user = "root",  
    _password = "1234"; 
    */
	/*
	 * private String _driver = "oracle.jdbc.driver.OracleDriver", _url =
	 * "jdbc:oracle:thin:@localhost:1521:orcl", _user = "scott", _password =
	 * "tiger";
	 */

    private boolean _traceOn = false;
    private boolean initialized = false;
	
	//占쎈슔�울옙�맶�뜝�럥�냻�뜝�럥�떚�뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� 10�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥�냻占쎈눀占쎈튂占쎄뎡
    private int _openConnections = 10;

    //占쎈슔�울옙�맶�뜝�럥�냻�뜝�럥�떚�뜝�럩援꿨뜝�룞�삕�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節뗫뼠占쎌맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
    private static DBConnectionMgr instance = null;

    //(2)dbmysql.properties占쎈쐻占쎈윥占쎈꺊占쎈쐻占쎈윪�앗껊쐻占쎈윪獄�占� 占쎈쐻占쎈윪�억옙占쎈쐻占쎈윥占쎄퐨占쎈쐻占쎈윥�뤃�쑚�쐻占쎈윥占쎈염占쎈쐻占쎈윞占쎈쭓 占쎈쐻占쎈윞占쎈��->占쎈쨬占쎈즴�뜝�뜫援�獄�占� �뜝�럡猿��뜝�럥�맄�뜝�럩紐좑옙�쐻占쎈윪亦낅¨堉⑨옙癒��굲
    public DBConnectionMgr() throws IOException {
    	Properties props=new Properties();
    	FileInputStream in=new FileInputStream
    			//("E:/webtest/4.jsp/sou/EightLibrary_test/WebContent/dbtest/dbmysql.properties");
    			("C:/webtest/4.jsp/sou/EightLibrary2/WebContent/dbtest/dbmysql.properties");
    	props.load(in);//占쎈쐻占쎈윥占쎈꺊占쎈쐻占쎈윪�앗껊쐻占쎈윪甕곤옙 占쎈쐻占쎈윞占쏙옙占쎈쐻占쎈윪占쎈츛 癲ル슢�닪占쏙옙�뜝�럡肄ュ뜝�럥�렓�뜝�럥遊억옙諭� �뜝�럡猿��뜝�럥�맄�뜝�럩紐좑옙�쐻占쎈윪亦낅¨堉⑨옙癒��굲
    	in.close();
    	_driver=props.getProperty("jdbc.drivers");
    	//占쎈쐻占쎈윥�뤃�뼹�쐻占쎈윪�앗껊쐻占쎈윪�얠쥜�삕占쎈��뜝�럩�뮛鶯ㅼ룊�삕 占쎈쐻占쎈윥筌묒궍�쐻占쎈윥獒뺤쉩�쐻占쎈윥�뜝�떥�슦�굲占쎄덩�뜝占� 占쎈쎗占쎈즵�몭琉꾩삕野껓옙
    	if(_driver!=null)  System.setProperty("jdbc.drivers", _driver);//占쎈쐻占쎈윥�떋�궍�뒙占쎈뙔占쎌굲
    	//-------------------------------------------------------------------
    	_url=props.getProperty("jdbc.url");
    	_user=props.getProperty("jdbc.username");
    	_password=props.getProperty("jdbc.password");
    	System.out.println("_driver=>"+(_driver)+",_url=>"+(_url));
    	System.out.println("_user=>"+(_user)+",_password=>"+(_password));
    }

    /** Use this method to set the maximum number of open connections before
     unused connections are closed.
     */
  
    //�뜝�럥�뒗占쎄콪占쎈빝筌믩끃�굲�뜝�뜦�뙔占쎌굲占쎈쐻占쎈짗占쎌굲�뜝�럥援� 占쎈쐻占쎈윥占쎄숱占쎈쐻占쎈윥占쎄퐨占쎈쐻占쎈윪亦낅‥�쐻占쎈윥獒뺧옙 占쎈쐻占쎈윪占쎌젳占쎈쐻占쎈윪占쎌뱾癲ル슢�닪占쎈씔占쎈빝占쎌뒱占쎌굲�뛾占썲뜝占�
    public static DBConnectionMgr getInstance() throws Exception{
        //�뜝�럥�뒗占쎄콪占쎈빝筌믩끃�굲�뜝�뜦�뙔占쎌굲占쎈쐻占쎈짗占쎌굲�뜝�럥�꼧 占쎈쐻占쎈윞�눧硫⑤쐻占쎈윞占쎈렰占쎈쐻占쎈윪�억옙 占쎈쐻占쎈윥占쎈떛占쎈쐻占쎈윥占쎈돌占쎈쐻占쎈윥占쎄퐨占쎈쐻占쎈윪�굢�뀘�쐻占쎈윥�젆袁ㅼ땡嚥〓끃�굲
		if (instance == null) {
            synchronized (DBConnectionMgr.class) {
                //占쎈쐻占쎈윞�눧硫⑤쐻占쎈윞占쎈렰占쎈쐻占쎈윪�억옙 占쎈쐻占쎈윥占쎈떛占쎈쐻占쎈윥占쎈돌占쎈쐻占쎈윥占쎄퐨占쎈쐻占쎈윪�굢�뀘�쐻占쎈윥�젆袁ㅼ땡嚥〓끃�굲
				if (instance == null) {
					//占쎈쨬占쎈즵�뜮�꼯�쇊占쎈츐占쎌굲濚밸㉡�삕占쎈쐻占쎈윞占쎈렰
                    instance = new DBConnectionMgr();
                }
            }
        }
        return instance;//占쎈쐻占쎈윪繹먮뜉�삕�뜮�뵃�삕獒뺣맢�눀�뜝占� 占쎈쐻占쎈윞繹먯궍�쐻占쎈윪占쎄데占쎈쐻占쎈윥獒뺤쉫異�占쎌삎占쎌쑋�뜝�럥�걬占쎌뒙占쎈뙔占쎌굲 占쎈쎗占쎈즵�몭諭꾩삕占쎄섬
    }
    //----------------------------------------------------------------------
    public void setOpenConnectionCount(int count) {
        _openConnections = count;
    }


    public void setEnableTrace(boolean enable) {
        _traceOn = enable;
    }


    /** Returns a Vector of java.sql.Connection objects */
    public Vector getConnectionList() {
        return connections;
    }


    /** Opens specified "count" of connections and adds them to the existing pool */
    //�뜝�럥�맶�뜝�럥堉뤹뼨轅명�ｈ굢占� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�몗�뤆�룇鍮섊뙼�뮋�삕占쎌맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�냲�뜝�럥�냷�뜝�럩援� �뜝�럥�맶�뜝�럥�넮�뜝�럥爰뽩뜝�럥荑덂뜝�럩援�

	public synchronized void setInitOpenConnections(int count)
            throws SQLException {

        Connection c = null;//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節륁삕
        ConnectionObject co = null;//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�몗�뤆�룇鍮섊뙼占�
		                           //�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�냲�뜝�럥�냷�뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節륁삕

        for (int i = 0; i < count; i++) {
			//count�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럡猿� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�몗�뤆�룇鍮섊뙼�뮋�삕占쎌맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
            c = createConnection();
			//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉잌뜝�럩�뮲�뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�몗�뤆�룇鍮섊뙼占�,�뜝�럥�맶�뜝�럥�쑅�뜝�럥�뿰�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
            co = new ConnectionObject(c, false);
             //�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉잌뜝�럩�뮲�뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�몗�뤆�룇鍮섊뙼�뮋�삕占쎌맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥�넰占썩벀�걠占쎄뎡
            connections.addElement(co);
            trace("ConnectionPoolManager: Adding new DB connection to pool (" + connections.size() + ")");
        }
    }


    /** Returns a count of open connections */
    public int getConnectionCount() {
        return connections.size();
    }


    /** Returns an unused existing or new connection.  */
    //�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�몗�뤆�룇鍮섊뙼�뮋�삕占쎌맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥�넮�뜝�럥爰뽩뜝�럥荑덂뜝�럩援�

	public synchronized Connection getConnection()
            throws Exception {
        if (!initialized) {
			//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� DB�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援뀐옙�뙑占쎈뼠占쎌맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥�넮嶺뚮ㅄ維�占쎈뉴�뜝�럩�뮲�뜝�럩援� �뜝�럥�맶�뜝�럥堉℡뜝�럥荑덂뜝�럩援�
            Class c = Class.forName(_driver);
			//�뜝�럥�맶�뜝�럥�넃�뜝�럥荑덂뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占�(�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援뀐옙�뙑占쎈뼠占쎌맶�뜝�럥六욕뜝�럥�뵰占쎄뎡�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占�)
            DriverManager.registerDriver((Driver) c.newInstance());

            initialized = true;//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�끋�뜝�럡�븫�뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� 
        }


        Connection c = null;
        ConnectionObject co = null;
		//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�몗�뤆�룇鍮섊뙼占�
        boolean badConnection = false;


        for (int i = 0; i < connections.size(); i++) {
			//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉잌뜝�럩�뮲�뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援뀐옙�뜗占쎈㎜占쎌굲 �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�몗�뤆�룇鍮섊뙼�뮋�삕占쎌맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥諭잌뜝�럥�냷�뜝�럩援�.
            co = (ConnectionObject) connections.elementAt(i);

            // If connection is not in use, test to ensure it's still valid!
            if (!co.inUse) {//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥諭잌뜝�럩逾쎾뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占� 
                try {
					//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�몗�뤆�룇鍮섊뙼占� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
                    badConnection = co.connection.isClosed();
                    if (!badConnection)
                        badConnection = (co.connection.getWarnings() != null);
                } catch (Exception e) {
                    badConnection = true;
                    e.printStackTrace();
                }

                // Connection is bad, remove from pool
                if (badConnection) { //�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉륅옙�벀�걠占쎄뎡 �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
				    //�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉잌뜝�럩�뮲�뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩逾쎾뜝�럩援�
                    connections.removeElementAt(i);
                    trace("ConnectionPoolManager: Remove disconnected DB connection #" + i);
                    continue;
                }

                c = co.connection;
                co.inUse = true;//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�

                trace("ConnectionPoolManager: Using existing DB connection #" + (i + 1));
                break;
            }
        }
        //�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節륁삕�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥諭잌뜝�럥堉꾤춯琉우뒩占쎄뎡(�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럥堉꾤춯琉우뒩占쎄뎡)
        if (c == null) {
            c = createConnection();
            co = new ConnectionObject(c, true);
            connections.addElement(co);//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉잌뜝�럩�뮲�뜝�럩援꿨뜝�럥�맶�뜝�럥�넰占썩벀�걠占쎄뎡

            trace("ConnectionPoolManager: Creating new DB connection #" + connections.size());
        }

        return c;//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�꼶
    }


    /** Marks a flag in the ConnectionObject to indicate this connection is no longer in use */
    //�뜝�럥�맶�뜝�럥�넦�뜝�럥爰뗥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�냲�뜝�럥�냷�뜝�럩援� �뜝�럥�맶�뜝�럥�넮�뜝�럥爰뽩뜝�럥荑덂뜝�럩援�
	public synchronized void freeConnection(Connection c) {
        if (c == null)//�뜝�럥�맶�뜝�럥�넦�뜝�럥爰뗥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�냲�뜝�럥�냷�뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�몗�뤆�룇鍮섊뙼�뮋�삕占쎌맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
            return;

        ConnectionObject co = null;

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            //�뜝�럥�맶�뜝�럥�넦�뜝�럥爰뗥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�몗�뤆�룇鍮섊뙼占�==�뜝�럥�맶�뜝�럥�넮嶺뚮ㅄ維�占쎈뉴�뜝�럡�븫�뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� 嶺뚢돦堉껓옙�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節륁삕
			if (c == co.connection) {
                co.inUse = false;//�뜝�럥�맶�뜝�럥�넦�뜝�럥爰뗥뜝�럩援꿰춯節뚭섈占쎌맶�뜝�럥吏쀥뜝�럩援�
                break;
            }
        }

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            //10�뜝�럥�맶�뜝�럥堉볟뜝�럡�븫�뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥堉⒵ㅀ袁㏉�∽옙爰뗥뜝�럩援� 
			//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�몗�뤆�룇鍮섊뙼�뮋�삕占쎌맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
			if ((i + 1) > _openConnections && !co.inUse)
                removeConnection(co.connection);
        }
    }

    public void freeConnection(Connection c, PreparedStatement p, ResultSet r) {
        try {
            if (r != null) r.close();
            if (p != null) p.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, Statement s, ResultSet r) {
        try {
            if (r != null) r.close();
            if (s != null) s.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, PreparedStatement p) {
        try {
            if (p != null) p.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, Statement s) {
        try {
            if (s != null) s.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /** Marks a flag in the ConnectionObject to indicate this connection is no longer in use */
    public synchronized void removeConnection(Connection c) {
        if (c == null)
            return;

        ConnectionObject co = null;
        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            if (c == co.connection) {
                try {
                    c.close();
                    connections.removeElementAt(i);
                    trace("Removed " + c.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }


    private Connection createConnection()
            throws SQLException {
        Connection con = null;

        try {
            if (_user == null)
                _user = "";
            if (_password == null)
                _password = "";

            Properties props = new Properties();
            props.put("user", _user);
            props.put("password", _password);

            con = DriverManager.getConnection(_url, props);
        } catch (Throwable t) {
            throw new SQLException(t.getMessage());
        }

        return con;
    }


    /** Closes all connections and clears out the connection pool */
    public void releaseFreeConnections() {
        trace("ConnectionPoolManager.releaseFreeConnections()");

        Connection c = null;
        ConnectionObject co = null;

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            if (!co.inUse)
                removeConnection(co.connection);
        }
    }


    /** Closes all connections and clears out the connection pool */
    public void finalize() {
        trace("ConnectionPoolManager.finalize()");

        Connection c = null;
        ConnectionObject co = null;

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            try {
                co.connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            co = null;
        }

        connections.removeAllElements();
    }


    private void trace(String s) {
        if (_traceOn)
            System.err.println(s);
    }

}

//inner class�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
class ConnectionObject {
	//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�몗�뤆�룇鍮섊뙼占�
    public java.sql.Connection connection = null;
    public boolean inUse = false;//�뜝�럥�맶�뜝�럥�쑅�뜝�럥�뿰�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�

    public ConnectionObject(Connection c, boolean useFlag) {
        connection = c;
        inUse = useFlag;
    }
}
