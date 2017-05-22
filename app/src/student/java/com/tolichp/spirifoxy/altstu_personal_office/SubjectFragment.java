package com.tolichp.spirifoxy.altstu_personal_office;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.tolichp.spirifoxy.altstu_personal_office.utils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubjectFragment extends Fragment {

    private String[] mGroupsArray = new String[] { "Курсовые работы", "Контрольные работы"};

    private String[] mWinterMonthsArray = new String[] { "Курсовая №1", "Курсовая №2"};
    private String[] mSpringMonthsArray = new String[] { "Контрольная работа №1", "Контрольная работа №2" };
    private WebView mWebView;


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }*/

    public static SubjectFragment newInstance() {//String param1, String param2) {
        SubjectFragment fragment = new SubjectFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        *//*if (id == R.id.action_settings) {
            return true;
        }  else *//*if (id == R.id.home) {
            getActivity().getSupportFragmentManager().popBackStack();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);


        //setTitle("Locale Date");

        Map<String, String> map;
        // коллекция для групп
        ArrayList<Map<String, String>> groupDataList = new ArrayList<>();
        // заполняем коллекцию групп из массива с названиями групп

        for (String group : mGroupsArray) {
            // заполняем список атрибутов для каждой группы
            map = new HashMap<>();
            map.put("groupName", group); // время года
            groupDataList.add(map);
        }

        // список атрибутов групп для чтения
        String groupFrom[] = new String[] { "groupName" };
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo[] = new int[] { android.R.id.text1 };

        // создаем общую коллекцию для коллекций элементов
        ArrayList<ArrayList<Map<String, String>>> сhildDataList = new ArrayList<>();

        // в итоге получится сhildDataList = ArrayList<сhildDataItemList>

        // создаем коллекцию элементов для первой группы
        ArrayList<Map<String, String>> сhildDataItemList = new ArrayList<>();
        // заполняем список атрибутов для каждого элемента
        for (String month : mWinterMonthsArray) {
            map = new HashMap<>();
            map.put("monthName", month); // название месяца
            сhildDataItemList.add(map);
        }
        // добавляем в коллекцию коллекций
        сhildDataList.add(сhildDataItemList);

        // создаем коллекцию элементов для второй группы
        сhildDataItemList = new ArrayList<>();
        for (String month : mSpringMonthsArray) {
            map = new HashMap<>();
            map.put("monthName", month);
            сhildDataItemList.add(map);
        }
        сhildDataList.add(сhildDataItemList);

//        // создаем коллекцию элементов для третьей группы
//        сhildDataItemList = new ArrayList<>();
//        for (String month : mSummerMonthsArray) {
//            map = new HashMap<>();
//            map.put("monthName", month);
//            сhildDataItemList.add(map);
//        }
//        сhildDataList.add(сhildDataItemList);
//
//        // создаем коллекцию элементов для четвертой группы
//        сhildDataItemList = new ArrayList<>();
//        for (String month : mAutumnMonthsArray) {
//            map = new HashMap<>();
//            map.put("monthName", month);
//            сhildDataItemList.add(map);
//        }
//        сhildDataList.add(сhildDataItemList);

        // список атрибутов элементов для чтения
        String childFrom[] = new String[] { "monthName" };
        // список ID view-элементов, в которые будет помещены атрибуты
        // элементов
        int childTo[] = new int[] { android.R.id.text1 };

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                getActivity(), groupDataList,
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, сhildDataList, android.R.layout.simple_list_item_1,
                childFrom, childTo);

        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.exlist_view_subject);
        expandableListView.setAdapter(adapter);


        TextView tvAnnotation = (TextView) view.findViewById(R.id.textview_annotation);
        final TextView tvIlias = (TextView) view.findViewById(R.id.textview_ilias);



        mWebView = (WebView) view.findViewById(R.id.webview);
        //mWebView.loadUrl("https://google.com");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setVisibility(View.GONE);
        /*ActionBar actionBar = getActivity().getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/




        tvAnnotation.setText("Test test test test test test test test test test test test test test test test test test test test test test test test test test test test test ");
        tvIlias.setText("https://habrahabr.ru/post/270121/");
        tvIlias.setText(Util.fromHtml("<a href=\"" + "https://habrahabr.ru/post/270121/" + "\">" + "https://habrahabr.ru/post/270121/" + "</a> "));
//        tvIlias.setText(Html.fromHtml("<a href=\"" + "https://habrahabr.ru/post/270121/" + "\">" + "https://habrahabr.ru/post/270121/" + "</a> "));
        //tvIlias.setMovementMethod(LinkMovementMethod.getInstance());
        tvIlias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl(tvIlias.getText().toString());
                mWebView.setVisibility(View.VISIBLE);
            }
        });


        setHasOptionsMenu(true);

        return view;//super.onCreateView(inflater, container, savedInstanceState);
    }
}
