package cl.moriahdp.church.contact.view;

import android.view.View;

import com.squareup.otto.Bus;

import cl.moriahdp.church.baseclasses.BaseFragmentView;
import cl.moriahdp.church.contact.fragment.ContactFragment;

public class ContactView extends BaseFragmentView {

    public ContactView(ContactFragment fragment, View rootView, Bus bus) {
        super(fragment, rootView, bus);
    }

}
