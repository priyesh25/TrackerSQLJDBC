package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tracker.nus.edu.sg.track.Create2Activity;
import tracker.nus.edu.sg.track.CreateActivity;
import tracker.nus.edu.sg.track.GridVActivity;
import tracker.nus.edu.sg.track.ListActivity;
import tracker.nus.edu.sg.track.R;
import tracker.nus.edu.sg.track.ReceiverActivity;
import tracker.nus.edu.sg.track.SearchViewActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ControlFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View mView;
    private Button bGrid, bCard, bSearch, bWeb;

    private OnFragmentInteractionListener mListener;

    public ControlFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ControlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ControlFragment newInstance(String param1, String param2) {
        ControlFragment fragment = new ControlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_control, container, false);

        bGrid = (Button) mView.findViewById(R.id.btnGridV);
        bCard = (Button) mView.findViewById(R.id.btnCardV);
        bSearch = (Button) mView.findViewById(R.id.btnSearchV);
        bWeb = (Button) mView.findViewById(R.id.btnWebV);
        bGrid.setOnClickListener(this);
        bCard.setOnClickListener(this);
        bSearch.setOnClickListener(this);
        bWeb.setOnClickListener(this);

        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    */

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.btnGridV:
                intent = new Intent(getActivity().getBaseContext(), GridVActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.btnCardV:
                intent = new Intent(getActivity().getBaseContext(), ReceiverActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSearchV:
                intent = new Intent(getActivity().getBaseContext(), SearchViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btnWebV:
                break;
            default:
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
