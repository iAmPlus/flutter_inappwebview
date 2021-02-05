package com.pichillilorenzo.flutter_inappwebview.CredentialDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class CredentialDatabaseHelper extends SQLiteOpenHelper {

  private static final String SQL_CREATE_PROTECTION_SPACE_TABLE =
          "CREATE TABLE " + ProtectionSpaceContract.FeedEntry.TABLE_NAME + " (" +
                  ProtectionSpaceContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                  ProtectionSpaceContract.FeedEntry.COLUMN_NAME_HOST + " TEXT NOT NULL," +
                  ProtectionSpaceContract.FeedEntry.COLUMN_NAME_PROTOCOL + " TEXT," +
                  ProtectionSpaceContract.FeedEntry.COLUMN_NAME_REALM + " TEXT," +
                  ProtectionSpaceContract.FeedEntry.COLUMN_NAME_PORT + " INTEGER," +
                  "UNIQUE(" + ProtectionSpaceContract.FeedEntry.COLUMN_NAME_HOST + ", " + ProtectionSpaceContract.FeedEntry.COLUMN_NAME_PROTOCOL + ", " +
                  ProtectionSpaceContract.FeedEntry.COLUMN_NAME_REALM + ", " + ProtectionSpaceContract.FeedEntry.COLUMN_NAME_PORT +
                  ")" +
          ");";

  private static final String SQL_CREATE_CREDENTIAL_TABLE =
          "CREATE TABLE " + CredentialContract.FeedEntry.TABLE_NAME + " (" +
                  CredentialContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                  CredentialContract.FeedEntry.COLUMN_NAME_USERNAME + " TEXT NOT NULL," +
                  CredentialContract.FeedEntry.COLUMN_NAME_PASSWORD + " TEXT NOT NULL," +
                  CredentialContract.FeedEntry.COLUMN_NAME_PROTECTION_SPACE_ID + " INTEGER NOT NULL," +
                  "UNIQUE(" + CredentialContract.FeedEntry.COLUMN_NAME_USERNAME + ", " + CredentialContract.FeedEntry.COLUMN_NAME_PASSWORD + ", " +
                  CredentialContract.FeedEntry.COLUMN_NAME_PROTECTION_SPACE_ID +
                  ")," +
                  "FOREIGN KEY (" + CredentialContract.FeedEntry.COLUMN_NAME_PROTECTION_SPACE_ID + ") REFERENCES " +
                  ProtectionSpaceContract.FeedEntry.TABLE_NAME + " (" + ProtectionSpaceContract.FeedEntry._ID + ") ON DELETE CASCADE" +
          ");";

  private static final String SQL_DELETE_PROTECTION_SPACE_TABLE =
          "DROP TABLE IF EXISTS " + ProtectionSpaceContract.FeedEntry.TABLE_NAME;

  private static final String SQL_DELETE_CREDENTIAL_TABLE =
          "DROP TABLE IF EXISTS " + CredentialContract.FeedEntry.TABLE_NAME;

  public CredentialDatabaseHelper(Context context) {
    super(context, CredentialDatabase.DATABASE_NAME, null, CredentialDatabase.DATABASE_VERSION);
  }

  public void onCreate(SQLiteDatabase db) {
//    db.execSQL(SQL_CREATE_PROTECTION_SPACE_TABLE);
//    db.execSQL(SQL_CREATE_CREDENTIAL_TABLE);
    SQLiteStatement stmnt1 = db.compileStatement("CREATE TABLE ?  ( ? INTEGER PRIMARY KEY, ?  TEXT NOT NULL, ?  TEXT, ?  TEXT, ?  INTEGER," + "UNIQUE( ? ,  ? , ? ,  ? ));");
    stmnt1.bindString(1, ProtectionSpaceContract.FeedEntry.TABLE_NAME);
    stmnt1.bindString(2,ProtectionSpaceContract.FeedEntry._ID);
    stmnt1.bindString(3, ProtectionSpaceContract.FeedEntry.COLUMN_NAME_HOST);
    stmnt1.bindString(4, ProtectionSpaceContract.FeedEntry.COLUMN_NAME_PROTOCOL);
    stmnt1.bindString(5,ProtectionSpaceContract.FeedEntry.COLUMN_NAME_REALM);
    stmnt1.bindString(6,ProtectionSpaceContract.FeedEntry.COLUMN_NAME_PORT);
    stmnt1.bindString(7,ProtectionSpaceContract.FeedEntry.COLUMN_NAME_HOST);
    stmnt1.bindString(8, ProtectionSpaceContract.FeedEntry.COLUMN_NAME_PROTOCOL);
    stmnt1.bindString(9,ProtectionSpaceContract.FeedEntry.COLUMN_NAME_REALM);
    stmnt1.bindString(10,ProtectionSpaceContract.FeedEntry.COLUMN_NAME_PORT);
    stmnt1.execute();
    SQLiteStatement stmnt2 = db.compileStatement( "CREATE TABLE ? (? INTEGER PRIMARY KEY,? TEXT NOT NULL,? TEXT NOT NULL,? INTEGER NOT NULL," +
            "UNIQUE(?, ?, ?), FOREIGN KEY (?) REFERENCES ? (?) ON DELETE CASCADE);");
    stmnt2.bindString(1,CredentialContract.FeedEntry.TABLE_NAME);
    stmnt2.bindString(2,CredentialContract.FeedEntry._ID);
    stmnt2.bindString(3,CredentialContract.FeedEntry.COLUMN_NAME_USERNAME);
    stmnt2.bindString(4,CredentialContract.FeedEntry.COLUMN_NAME_PASSWORD);
    stmnt2.bindString(5,CredentialContract.FeedEntry.COLUMN_NAME_PROTECTION_SPACE_ID);
    stmnt2.bindString(6,CredentialContract.FeedEntry.COLUMN_NAME_USERNAME);
    stmnt2.bindString(7,CredentialContract.FeedEntry.COLUMN_NAME_PASSWORD);
    stmnt2.bindString(8,CredentialContract.FeedEntry.COLUMN_NAME_PROTECTION_SPACE_ID);
    stmnt2.bindString(9,CredentialContract.FeedEntry.COLUMN_NAME_PROTECTION_SPACE_ID);
    stmnt2.bindString(10,ProtectionSpaceContract.FeedEntry.TABLE_NAME);
    stmnt2.bindString(11,ProtectionSpaceContract.FeedEntry._ID);
    stmnt2.execute();
  }

  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // This database is only a cache for online data, so its upgrade policy is
    // to simply to discard the data and start over
    SQLiteStatement stmnt1 = db.compileStatement("DROP TABLE IF EXISTS ?");
    stmnt1.bindString(1,ProtectionSpaceContract.FeedEntry.TABLE_NAME);
    stmnt1.execute();
    SQLiteStatement stmnt2 = db.compileStatement("DROP TABLE IF EXISTS ?");
    stmnt2.bindString(1,CredentialContract.FeedEntry.TABLE_NAME);
    stmnt2.execute();
//    db.execSQL(SQL_DELETE_PROTECTION_SPACE_TABLE,new Object[]{ProtectionSpaceContract.FeedEntry.TABLE_NAME});
//    db.execSQL(SQL_DELETE_CREDENTIAL_TABLE);
    onCreate(db);
  }

  public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    onUpgrade(db, oldVersion, newVersion);
  }

  public void clearAllTables(SQLiteDatabase db) {
//    db.execSQL(SQL_DELETE_PROTECTION_SPACE_TABLE);
//    db.execSQL(SQL_DELETE_CREDENTIAL_TABLE);
    SQLiteStatement stmnt1 = db.compileStatement("DROP TABLE IF EXISTS ?");
    stmnt1.bindString(1,ProtectionSpaceContract.FeedEntry.TABLE_NAME);
    stmnt1.execute();
    SQLiteStatement stmnt2 = db.compileStatement("DROP TABLE IF EXISTS ?");
    stmnt2.bindString(1,CredentialContract.FeedEntry.TABLE_NAME);
    stmnt2.execute();
    onCreate(db);
  }
}
