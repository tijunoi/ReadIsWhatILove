package nilordonez.com.profileitem;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ProfileSignout extends ConstraintLayout implements View.OnClickListener {

    private String mUserEmailStr;
    private TextView mUserEmailTextView;
    private Button mSignoutButton;
    private OnSignoutButtonClickedListener mCallback;

    public ProfileSignout(Context context) {
        super(context);
        init(null, 0);
    }

    public ProfileSignout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ProfileSignout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_profile_signout, this);

        mUserEmailTextView = findViewById(R.id.user_email_textview);
        mSignoutButton = findViewById(R.id.signout_button);
        mSignoutButton.setOnClickListener(this);
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ProfileSignout, defStyle, 0);

        mUserEmailStr = a.getString(R.styleable.ProfileSignout_userEmail);
        if (mUserEmailStr != null) {
            setUserEmail();
        }

        a.recycle();

    }

    private void setUserEmail() {
        mUserEmailTextView.setText(mUserEmailStr);
        invalidate();
        requestLayout();
    }

    public interface OnSignoutButtonClickedListener {
        public void onSignoutButtonClicked(ProfileSignout source);
    }

    public void setOnSignoutButtonClickedListener(OnSignoutButtonClickedListener listener){
        mCallback = listener;
    }


    /**
     * Obtiene el email actual del item
     *
     * @return El email actual del item
     */
    public String getUserEmail() {
        return mUserEmailStr;
    }

    /**
     * Setea el email del usuario en la view
     *
     * @param userEmail El email a settear
     */
    public void setUserEmail(String userEmail) {
        mUserEmailStr = userEmail;
        setUserEmail();
    }

    @Override
    public void onClick(View view) {
        if (mCallback != null) {
            mCallback.onSignoutButtonClicked(this);
        }
    }
}
