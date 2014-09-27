package com.testes.receiver;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.testes.android.R;

public class SMSReceiver extends BroadcastReceiver{

	private SharedPreferences preferences;
	public static final String SMS_LOG = "log";
	@Override
	public void onReceive(Context context, Intent intent) {

		if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
			preferences = PreferenceManager.getDefaultSharedPreferences(context);
			Editor editor = preferences.edit();
			Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
			SmsMessage[] msgs = null;
			String msg_from;
			if (bundle != null){
				//---retrieve the SMS message received---
				try{
					Object[] pdus = (Object[]) bundle.get("pdus");
					msgs = new SmsMessage[pdus.length];
					for(int i=0; i<msgs.length; i++){
						msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
						msg_from = msgs[i].getOriginatingAddress();
						String msgBody = msgs[i].getMessageBody();
					}
					Date date = new Date(msgs[0].getTimestampMillis());
					String logValue = "SMS from: "+msgs[0].getOriginatingAddress() + " "+ date.toString()+" "+msgs[0].getMessageBody();
					String previousLog = preferences.getString(SMS_LOG, "");
					Log.i("SMSReceiver","SMS from: "+msgs[0].getOriginatingAddress() + " "+ date.toString()+" "+msgs[0].getMessageBody());

					//save info in preferences
					editor.putString(SMS_LOG, previousLog+"\n"+logValue).commit();

					//                   //send email
					//                   Intent sendMailIntent = new Intent(Intent.ACTION_SEND);
					//                   sendMailIntent.setType("plain/text");
					//                   sendMailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					//                   sendMailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"joao_amarosilva@hotmail.com"});
					//                   sendMailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject");
					//                   sendMailIntent.putExtra(Intent.EXTRA_TEXT   , msgs[0].getMessageBody().toString());
					//                   try {
					//                	   context.getApplicationContext().startActivity(Intent.createChooser(sendMailIntent, "Send mail..."));
					//                   } catch (ActivityNotFoundException ex) {
					//                	   ex.printStackTrace();
					//                	   System.out.println("fail");
					//                       Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
					//                   }
					//                 catch (NullPointerException enx) {
					//             	  enx.printStackTrace();
					//                }

					//                   try {
					//			        	MailSender.sendMail(
					//			        	context.getText(R.string.mailSenderUsername).toString(),
					//			        	context.getText(R.string.mailSenderPassword).toString(),
					//			        	context.getText(R.string.mailSenderHost).toString(),
					//			        	context.getText(R.string.mailSenderPort).toString(),
					//			        	Boolean.valueOf(context.getText(R.string.mailSenderIsAuthenticated).toString()),
					//			        	context.getText(R.string.mailSenderUsername).toString(),
					//			        	"joaoamaro@boldint.com",
					//			        	"coisas sms",
					//			        	msgs[0].getMessageBody().toString(),
					//			        	Boolean.valueOf(context.getText(R.string.mailSenderIsHtml).toString()));
					//			        	
					//Show dialog for successful request
					AlertDialog successDialog = new AlertDialog.Builder(context)
					.setMessage("ok")					      
					.setPositiveButton(context.getString(R.string.yes), new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {

						}
					})
					.create();

					//successDialog.show();

					//} 
					//			        catch (AddressException e) {
					//			        	e.printStackTrace();
					//			        	} 
					//			        catch (MessagingException e) {
					//			        	e.printStackTrace();
					//			        	}
				}

				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

}