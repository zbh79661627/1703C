package com.bw.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class GenEntityOracle {

    private String authorName = "liuerdong";// ��������
    private String tablename = "eb_brand";// ����
    private String packageOutPath = "com.bw.bean";// ָ��ʵ���������ڰ���·��
    private String[] colnames; // ��������
    private String[] colTypes; // ������������
    private int[] colLens; // �ֶγ�������
    private String[] colComments;  //��ע����
    private String[] tablecolnames;
    private int[] colSizes; // ������С����
    private boolean f_util = false; // �Ƿ���Ҫ�����java.util.*
    private boolean f_sql = false; // �Ƿ���Ҫ�����java.sql.*
    private String javaName = "";

    private String databaseName = "";//mysql���ݿ���

    // ���ݿ�����
//    private static final String URL = "jdbc:oracle:thin:@192.168.215.128:1521:orcl";
//    private static final String NAME = "scott";
//    private static final String PASS = "tiger";
    private static final String URL="jdbc:mysql://localhost:3306/1703c?username=root&password=200101t&useUnicode=true&characterEncoding=UTF8";
//  private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    

    public GenEntityOracle() {
        this("pingtai_kaohezhouqi","","");
    }
    
    public GenEntityOracle(String tablename,String javaName,String databaseName) {
    	this.javaName = javaName;
        this.tablename = tablename;
        this.databaseName = databaseName;
       
        //�ж�������»��߾ͽ�ȡ�����򲻽�ȡ
        if(tablename.indexOf("_")>0){
        	javaName = tablename.split("_")[1];
        }else{
        	javaName = tablename;
        }
        
    }
    
    public void init() {
        // ��������
        Connection con;
        // ��Ҫ����ʵ����ı�
        String sql = "select * from " + tablename;
        Statement pStemt = null;
        try {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            //��ȡע��
            //mysql������Ҫ��ĵĵط� 		��������databaseName 	mysql��ѯע�ͺ�oracle��ͬ
            String comSql = "select column_comment from information_schema.COLUMNS where TABLE_SCHEMA='"+databaseName+"' and  table_name='"+tablename.toUpperCase()+"'";
            // String comSql = "select * from user_col_comments where  table_name='"+tablename.toUpperCase()+"'";
//            Connection ccon = DriverManager.getConnection(URL, NAME, PASS);
            Connection ccon = DriverManager.getConnection(URL);
            Statement stmt = ccon.createStatement();
            ResultSet cRs = stmt.executeQuery(comSql);
            
//            con = DriverManager.getConnection(URL, NAME, PASS);
            con = DriverManager.getConnection(URL);
            pStemt = (Statement) con.createStatement();
            ResultSet rs = pStemt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int size = rsmd.getColumnCount(); // ͳ����
            colnames = new String[size];
            colTypes = new String[size];
            colLens = new int[size];
            colComments = new String[size];
            colSizes = new int[size];
            int j = 0;
            while(cRs.next()) {
            	//MySQL��Ҫ�ĵĵط�  ӦΪ��select column_comment   ���Բ�ѯ��һ����  ���1 3��ʾע�������е�λ��
                colComments[j] = cRs.getString(1);
//                colComments[j] = cRs.getString(3);
                j++;
            }
            for (int i = 0; i < size; i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);
                colLens[i] = rsmd.getColumnDisplaySize(i + 1);
                
                if (colTypes[i].equalsIgnoreCase("date")
                        || colTypes[i].equalsIgnoreCase("timestamp")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("blob")
                        || colTypes[i].equalsIgnoreCase("char")) {
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
            tablecolnames = colnames.clone();
            for(int i=0;i<colnames.length;i++) {
                String s = colnames[i];
                String[] strs = s.split("_");
                colnames[i] = "";
                for(int k=0;k<strs.length;k++) {
                	//�ж�  ��һ��Ԫ��Ĭ��ȫСд
                    if(k == 0){
                    	colnames[i] += strs[k].toLowerCase();
                    }else{
                    	//ʵ�ִӵڶ����ʿ�ʼ����ĸ��д
                    	String num = strs[k].toLowerCase();
                    	colnames[i] += num.substring(0,1).toUpperCase()+num.substring(1);
                    }
                }
            }
            
            String content = parse(colnames, colTypes, colSizes);

            try {
                File directory = new File("");
                // System.out.println("����·����"+directory.getAbsolutePath());
                // System.out.println("���·����"+directory.getCanonicalPath());
                String path = this.getClass().getResource("").getPath();

                System.out.println(path);
                // System.out.println("src/?/"+path.substring(path.lastIndexOf("/com/",
                // path.length())) );
                // String outputPath = directory.getAbsolutePath()+
                // "/src/"+path.substring(path.lastIndexOf("/com/",
                // path.length()), path.length()) + initcap(tablename) +
                // ".java";
                //���Ŀ¼   ��Ҫ�䶯��ʱ���һ��
                String outputPath = directory.getAbsolutePath()
                        + "/src/"
                        + this.packageOutPath.replace(".", "/") + "/"
                        + initcap(javaName) + ".java";
                FileWriter fw = new FileWriter(outputPath);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(content);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // try {
            // con.close();
            // } catch (SQLException e) {
            // e.printStackTrace();
            // }
        }
    }

    /**
     * ���ܣ�����ʵ�����������
     * 
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();

        sb.append("package " + this.packageOutPath + ";\r\n");
        sb.append("\r\n");
        
        // �ж��Ƿ��빤�߰�
        if (f_util) {
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }
        
        // ע�Ͳ���
//        sb.append("   /**\r\n");
//        sb.append("    * " + javaName + " ʵ����\r\n");
//        sb.append("    * " + new Date() + " " + this.authorName + "\r\n");
//        sb.append("    */ \r\n");
        SimpleDateFormat sm = new SimpleDateFormat("yyyy��MM��dd�� aHH:mm:ss");
		String time = sm.format(new Date());
		Calendar date = Calendar.getInstance();
		
        // ʵ�岿��
        sb.append("\r\npublic class " + initcap(javaName) + " implements java.io.Serializable {\r\n");
        processAllAttrs(sb);// ����
        processAllMethod(sb);// get set����
        sb.append("}\r\n");

        // System.out.println(sb.toString());
        return sb.toString();
    }
    /**
     * ���ܣ��������з���
     * 
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String now = sdf.format(date);
        
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\r\n\tpublic void set" + initcap(colnames[i]) + "("
                    + sqlType2JavaType(colTypes[i]) + " " + colnames[i]
                    + "){\r\n");
            sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
            
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get"
                    + initcap(colnames[i]) + "(){\r\n");
            sb.append("\t\treturn " + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
            
        }
        
        
    }
    /**
     * ���ܣ�������������
     * 
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {
        sb.append("\r\n\t//columns START\r\n");
        String str = "";
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " "
                    + colnames[i] + ";\r\n");
            str+=","+sqlType2JavaType(colTypes[i])+" "+colnames[i];
        }
        str = str.substring(1);
        
        sb.append("\t//columns END\r\n");

        sb.append("\tpublic "+javaName+"(){\r\n");
        sb.append("\r\t}\r\n\r");
       
        sb.append("\tpublic "+javaName+"("+str+"){\r\n");
        for (int i = 0; i < colnames.length; i++) {
	        if(i!=colnames.length-1){
	        	sb.append("\t\tthis."+colnames[i]+"="+colnames[i]+";\r");
	        }else{
	        	sb.append("\t\tthis."+colnames[i]+"="+colnames[i]+";");
	        }
        }
        sb.append("\r\t}\r\n");
        
        
    }


    /**
     * ���ܣ��������ַ���������ĸ�ĳɴ�д
     * 
     * @param str
     * @return
     */
    private String initcap(String str) {

        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * ���ܣ�����е���������
     * 
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType) {

        if (sqlType.equalsIgnoreCase("binary_double")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("binary_float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar2")
                || sqlType.equalsIgnoreCase("varchar2")
                || sqlType.equalsIgnoreCase("varchar")
                || sqlType.equalsIgnoreCase("blob")
                || sqlType.equalsIgnoreCase("clob")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("date")
                || sqlType.equalsIgnoreCase("timestamp")
                || sqlType.equalsIgnoreCase("timestamp with local time zone")
                || sqlType.equalsIgnoreCase("timestamp with time zone")) {
//            return "Date";
        	return "String";
        } else if (sqlType.equalsIgnoreCase("number") || sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        }
        return "String";
    }
    public static void main(String[] args) {  
        //�������Ǳ���  ���ɵ�ʵ������ĸ��д   ������User
    	//��һ����������    �ڶ�������ʵ������    �������������ݿ�����
        /*new GenEntityOracle("tb_user","User","1703c").init(); */
        new GenEntityOracle("dp","Dp","test").init(); 
        new GenEntityOracle("brand","Brand","test").init(); 
        new GenEntityOracle("d_b","DB","test").init(); 
       
    }  
}