package pw.jawedyx.maksi_01;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class CardFragment extends Fragment {

    public static final String MOTTO_FONT = "fonts/MarckScript-Regular.ttf";

    private CheckBox acceptCardCheckBox;
    private EditText editCard;
    private Button okButton;


    public CardFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.fragment_card, container, false);

        if (App.getSettings().contains(App.PREFERENCES_CARDNUMBER)){ //Если привязан номер карты

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new LoggedCardFragment(), "visible-fragment").commit();

        }else{
            //Шрифт для слогана на карте
            TextView motto = (TextView) v.findViewById(R.id.card_motto);
            Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),MOTTO_FONT);
            motto.setTypeface(typeface);

            acceptCardCheckBox = (CheckBox)v.findViewById(R.id.accept_cb);
            editCard = (EditText) v.findViewById(R.id.editcard);
            okButton = (Button) v.findViewById(R.id.okbutton);

            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String cardnumber = editCard.getText().toString();

                    if(cardnumber.length() == 8 & !cardnumber.equals(getActivity().getString(R.string.default_card_value)) ){

                        if(acceptCardCheckBox.isChecked()){

                            SharedPreferences.Editor editor = App.getSettings().edit();
                            editor.putString(App.PREFERENCES_CARDNUMBER, cardnumber);
                            editor.apply();

                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.content_frame, new LoggedCardFragment(), "visible-fragment").commit();


                        }else{
                            Toast.makeText(getActivity(), "Вы должны являться владельцем карты", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getActivity(), "Карты с таким номером не существует", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }




        return v;
    }




}
