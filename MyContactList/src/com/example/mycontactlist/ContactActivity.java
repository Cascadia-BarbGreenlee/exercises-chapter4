package com.example.mycontactlist;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.text.style.BackgroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.mycontactlist.DatePickerDialog.SaveDateListener;

public class ContactActivity extends FragmentActivity implements SaveDateListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        
        initListButton();
        initMapButton();
        initSettingsButton();
        initToggleButton();
        setForEditing(false);
        
        initChangeDateButton();
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initListButton(){
    	ImageButton list = (ImageButton)findViewById(R.id.imageButtonList);
    	list.setOnClickListener(new View.OnClickListener() {
    		public void onClick (View v){
    			Intent intent = new Intent(ContactActivity.this, ContactListActivity.class);
    			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    			startActivity(intent);
    		}
    	});
    }
    
    private void initMapButton(){
    	ImageButton map = (ImageButton)findViewById(R.id.imageButtonMap);
    	map.setOnClickListener(new View.OnClickListener() {
    		public void onClick (View v){
    			Intent intent = new Intent(ContactActivity.this, ContactMapActivity.class);
    			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    			startActivity(intent);
    		}
    	});
    }
	
    private void initSettingsButton(){
    	ImageButton settings = (ImageButton)findViewById(R.id.imageButtonSettings);
    	settings.setOnClickListener(new View.OnClickListener() {
    		public void onClick (View v){
    			Intent intent = new Intent(ContactActivity.this, ContactSettingsActivity.class);
    			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    			startActivity(intent);
    		}
    	});
    }
    
    private void initToggleButton(){
    	final ToggleButton editToggle = (ToggleButton)findViewById(R.id.toggleButtonEdit);
    	editToggle.setOnClickListener(new OnClickListener(){
    		
    		@Override
    		public void onClick(View arg0){
    			setForEditing(editToggle.isChecked());
    			highlightEditFields();
    		}
    	});
    }
    
    private void setForEditing(boolean enabled){
    	EditText editName = (EditText)findViewById(R.id.editName);
    	EditText editAddress = (EditText)findViewById(R.id.editAddress);
    	EditText editCity = (EditText)findViewById(R.id.editCity);
    	EditText editState = (EditText)findViewById(R.id.editState);
    	EditText editZipCode = (EditText)findViewById(R.id.editZipcode);
    	EditText editPhone = (EditText)findViewById(R.id.editHome);
    	EditText editCell = (EditText)findViewById(R.id.editCell);
    	EditText editEmail = (EditText)findViewById(R.id.editEMail);
    	Button buttonChange = (Button)findViewById(R.id.btnBirthday);
    	Button buttonSave = (Button)findViewById(R.id.buttonSave);
    	  
    	Drawable originalDrawable = editName.getBackground();
    	
    	editName.setEnabled(enabled);
    	editAddress.setEnabled(enabled);
    	editCity.setEnabled(enabled);
    	editState.setEnabled(enabled);
    	editZipCode.setEnabled(enabled);
    	editPhone.setEnabled(enabled);
    	editCell.setEnabled(enabled);
    	editEmail.setEnabled(enabled);
    	buttonChange.setEnabled(enabled);
    	buttonSave.setEnabled(enabled);
    	
    	if(enabled){
    		editName.requestFocus();
    		editName.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
    		editAddress.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
    		editCity.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
    		editState.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
    		editZipCode.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
    		editPhone.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
    		editCell.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
    		editEmail.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
    	}
    	else{
    		ScrollView s = (ScrollView)findViewById(R.id.scrollView1);
    		s.clearFocus();
    		editName.setBackgroundResource(android.R.drawable.editbox_background_normal);
    		editAddress.setBackgroundResource(android.R.drawable.editbox_background_normal);
    		editCity.setBackgroundResource(android.R.drawable.editbox_background_normal);
    		editState.setBackgroundResource(android.R.drawable.editbox_background_normal);
    		editZipCode.setBackgroundResource(android.R.drawable.editbox_background_normal);
    		editPhone.setBackgroundResource(android.R.drawable.editbox_background_normal);
    		editCell.setBackgroundResource(android.R.drawable.editbox_background_normal);
    		editEmail.setBackgroundResource(android.R.drawable.editbox_background_normal);
    	}
    }
    

	@Override
	public void didFinishDatePickerDialog(Time selectedTime) {
		TextView birthDay = (TextView)findViewById(R.id.textBirthday);
		birthDay.setText(DateFormat.format("MM/dd/yyyy", selectedTime.toMillis(false)).toString());
		
	}
	
	private void initChangeDateButton(){
		Button changeDate = (Button)findViewById(R.id.btnBirthday);
		changeDate.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				FragmentManager fm = getSupportFragmentManager();
				DatePickerDialog datePickerDialog = new DatePickerDialog();
				datePickerDialog.show(fm,"DatePick");
			}
		});
	}
	
	private void highlightEditFields(){
		//EditText editName = (EditText)findViewById(R.id.editName);
    	//EditText editAddress = (EditText)findViewById(R.id.editAddress);
    	//EditText editCity = (EditText)findViewById(R.id.editCity);
    	//EditText editState = (EditText)findViewById(R.id.editState);
    	//EditText editZipCode = (EditText)findViewById(R.id.editZipcode);
    	//EditText editPhone = (EditText)findViewById(R.id.editHome);
    	//EditText editCell = (EditText)findViewById(R.id.editCell);
    	//EditText editEmail = (EditText)findViewById(R.id.editEMail);
    	
		//editName.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
		//editAddress.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
		//editCity.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
		//editState.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
		//editZipCode.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
		//editPhone.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
		//editCell.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
		//editEmail.setBackgroundColor(getResources().getColor(R.color.dataEntry_background));
	}
	
	private void unHighlightEditFields(){
		
	}
      
}
