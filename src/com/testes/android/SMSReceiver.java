package com.testes.android;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.testes.android.R;

public class SMSReceiver extends BroadcastReceiver{

    private SharedPreferences preferences;
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
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
                   System.out.println("from: "+msgs[0].getOriginatingAddress() + " "+ msgs[0].getTimestampMillis()+" "+msgs[0].getMessageBody());
               
                   Intent i = new Intent(Intent.ACTION_SEND);
                   i.setType("plain/text");
                   i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                   i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"joao_amarosilva@hotmail.com"});
                   i.putExtra(Intent.EXTRA_SUBJECT, "coisas");
                   i.putExtra(Intent.EXTRA_TEXT   , msgs[0].getMessageBody().toString());
                   try {
                	   context.startActivity(Intent.createChooser(i, "Send mail..."));
                   } catch (ActivityNotFoundException ex) {
                	   System.out.println("fail");
                       Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                   }
                 catch (NullPointerException enx) {
             	  enx.printStackTrace();
                }
                 
                   //try {
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
//			        	//Show dialog for successful request
//						AlertDialog successDialog = new AlertDialog.Builder(context)
//						.setMessage("ok")
//					      
//					       .setPositiveButton(context.getString(R.string.yes), new DialogInterface.OnClickListener() {
//					           public void onClick(DialogInterface dialog, int whichButton) {
//					           	
//					           }
//					       })
//					      .create();
					
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