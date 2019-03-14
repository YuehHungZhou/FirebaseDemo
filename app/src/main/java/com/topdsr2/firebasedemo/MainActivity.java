package com.topdsr2.firebasedemo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String JILL = "jill";
    private static final String YH = "YH";
    private static final String JENNY = "jenny";
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String ARTICLE = "Article";
    private static final String USER = "User";
    private static final String ARTICLE_TITLE = "article_title";
    private static final String ARTICLE_ID = "article_id";
    private static final String ARTICLE_CONTENT = "article_content";
    private static final String ARTICLE_TAG = "article_tag";
    private static final String ARTICLE_JOKE = "Joke";
    private static final String ARTICLE_BEAUTY = "Beauty";
    private static final String ARTICLE_GOSSIPING = "Gossiping";
    private static final String ARTICLE_SCHOOLLIFE = "SchoolLife";
    private static final String AUTHOR = "author";
    private static final String CREATED_TIME = "created_time";

    private static final String YH_KEY = "LUhNHrgBZVjqPsZCCur5";
    private static final String JILL_KEY = "hZuZZy9hhuZPeuRPEuHn";
    private static final String JENNY_KEY = "1bfiaXWMufcv4MihZKn5";


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button mButton;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;
    private EditText mEditText;
    private EditText mEditText2;
    private EditText mEditText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
        mButton4 = findViewById(R.id.button4);
        mButton6 = findViewById(R.id.button6);
        mButton7 = findViewById(R.id.button7);
        mButton8 = findViewById(R.id.button8);
        mEditText = findViewById(R.id.editText);
        mEditText2 = findViewById(R.id.editText2);
        mEditText3 = findViewById(R.id.editText3);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mEditText.getText().toString();
                addFreind(str);
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEditText2.getText().toString();
                String tag = mEditText3.getText().toString();
                addArticle(content , tag);
            }
        });

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mEditText.getText().toString();
                acceptApply(str);
            }
        });

        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mEditText.getText().toString();
                cancelApply(str);
            }
        });

        mButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String friend = mEditText2.getText().toString();
                String tag = mEditText3.getText().toString();

                if ("".equals(friend) && "".equals(tag)){
                    showTotalArticle();
                }else if ("".equals(friend) && !"".equals(tag)){
                    showTAGArticle(tag);
                }else if (!"".equals(friend) && "".equals(tag)){
                    showFriendArticle(friend);
                }else if (!"".equals(friend) && !"".equals(tag)){
                    showFriendTAGArticle(friend,tag);
                }
            }
        });

        mButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFriendlist();
            }
        });

        mButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showApplylist();
            }
        });



        final DocumentReference docRefYZ = db.collection(USER).document(YH_KEY);
        docRefYZ.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    User user = snapshot.toObject(User.class);
                    if (user.getApply() != null) {
                        for (String str : user.getApply()) {
                            Log.v("YH apply change : ", str);
                        }
                    }
                    if (user.getCheck() != null) {
                        for (String str : user.getCheck()) {
                            Log.v("YH check change : ", str);
                        }
                    }
                    if (user.getFriend() != null) {
                        for (String str : user.getFriend()) {
                            Log.v("YH friend change : ", str);
                        }
                    }
                } else {
                    Log.d("Current data", "null");
                }
            }
        });

        final DocumentReference docRefJENNY = db.collection(USER).document(JENNY_KEY);
        docRefJENNY.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    User user = snapshot.toObject(User.class);
                    if (user.getApply() != null) {
                        for (String str : user.getApply()) {
                           Log.d("Jenny apply change : ", str);
                        }
                    }
                    if (user.getCheck() != null) {
                        for (String str : user.getCheck()) {
                            Log.d("Jenny check change : ", str);
                        }
                    }
                    if (user.getFriend() != null) {
                        for (String str : user.getFriend()) {
                            Log.d("Jenny friend change : ", str);
                        }
                    }
                } else {
                    Log.d("Current data", "null");
                }
            }
        });

        final DocumentReference docRefJILL = db.collection(USER).document(JILL_KEY);
        docRefJILL.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    User user = snapshot.toObject(User.class);
                    if (user.getApply() != null) {
                        for (String str : user.getApply()) {
                           Log.e("jill apply change : ", str);
                        }
                    }
                    if (user.getCheck() != null) {
                        for (String str : user.getCheck()) {
                           Log.e("jill check change : ", str);
                        }
                    }
                    if (user.getFriend() != null) {
                        for (String str : user.getFriend()) {
                            Log.e("jill friend change : ", str);
                        }
                    }
                } else {
                    Log.d("Current data", "null");
                }
            }
        });

    }

    public void  acceptApply(final String id){

        db.collection(USER)
                .whereEqualTo(ID, id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots){
                            User user = document.toObject(User.class);
                            Map<String,Object> updateYHfriend = new HashMap<>();
                            updateYHfriend.put("friend", FieldValue.arrayUnion(user.getName()));
                            db.collection(USER).document(YH_KEY)
                                    .update(updateYHfriend);

                            Map<String,Object> updateYHcheck = new HashMap<>();
                            updateYHcheck.put("apply", FieldValue.arrayRemove(id));
                            db.collection(USER).document(YH_KEY)
                                    .update(updateYHcheck);

                            Map<String,Object> updateJILLfriend = new HashMap<>();
                            updateJILLfriend.put("friend", FieldValue.arrayUnion(YH));
                            db.collection(USER).document(document.getId())
                                    .update(updateJILLfriend);

                            Map<String,Object> updateJILLapply = new HashMap<>();
                            updateJILLapply.put("check", FieldValue.arrayRemove("1"));
                            db.collection(USER).document(document.getId())
                                    .update(updateJILLapply);
                        }
                    }
                });
    }


    public void  cancelApply(final String id){

        db.collection(USER)
                .whereEqualTo(ID, id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots){
                            User user = document.toObject(User.class);

                            Map<String,Object> updatesYH = new HashMap<>();
                            updatesYH.put("apply", FieldValue.arrayRemove(id));
                            db.collection(USER).document(YH_KEY)
                                    .update(updatesYH);

                            Map<String,Object> updatesJILL = new HashMap<>();
                            updatesJILL.put("check", FieldValue.arrayRemove("1"));
                            db.collection(USER).document(document.getId())
                                    .update(updatesJILL);
                        }
                    }
                });
    }

    public void addArticle(final String content , final String tag) {
        db.collection(ARTICLE).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                Map<String, Object> articleTest = new HashMap<>();
                articleTest.put(ARTICLE_ID, Integer.toString(queryDocumentSnapshots.size() + 1));
                articleTest.put(ARTICLE_TITLE, "YH Article");
                articleTest.put(ARTICLE_CONTENT, content);
                articleTest.put(ARTICLE_TAG, tag);
                articleTest.put(AUTHOR, YH);
                articleTest.put(CREATED_TIME, FieldValue.serverTimestamp());

                db.collection(ARTICLE).add(articleTest);
            }
        });
    }


    public void addFreind(final String friendName) {

        db.collection(USER)
                .whereEqualTo(NAME, YH)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                    if (user.getFriend().size() == 0){
                                        addCheck(document.getId(), friendName);
                                        addApply(friendName, user.getId());
                                    }

                                    for (String str : user.getFriend()) {
                                        if (!str.equals(friendName)) {
                                            addCheck(document.getId(), friendName);
                                            addApply(friendName, user.getId());
                                        } else {
                                            Log.d("was friend ", friendName);
                                        }
                                    }
                            }
                        } else {
                            Log.w("get", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void addCheck(final String keyID, String friendName) {
        db.collection(USER)
                .whereEqualTo(NAME, friendName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                db.collection(USER).document(keyID)
                                        .update("check", FieldValue.arrayUnion(user.getId()));
                            }
                        } else {
                            Log.w("get", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void addApply(String friendName, final String myid) {
        db.collection(USER)
                .whereEqualTo(NAME, friendName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                db.collection(USER).document(document.getId())
                                        .update("apply", FieldValue.arrayUnion(myid));
                            }
                        } else {
                            Log.w("get", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void showFriendlist(){

        db.collection(USER)
                .whereEqualTo(NAME, YH)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                String friendList ="";

                                for (String str : user.getFriend()){
                                    friendList =  friendList + " [ " + str + " ] ";
                                }
                                showToast(friendList);
                            }
                        } else {
                            Log.w("get", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void showToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

    public void showApplylist(){
        db.collection(USER)
                .whereEqualTo(NAME, YH)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                String applyList ="";
                                for (String str : user.getApply()){
                                    applyList = applyList + " [ " + str + " ] ";
                                }
                                showToast(applyList);
                            }
                        } else {
                            Log.w("get", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void showFriendArticle(String name) {
        db.collection(ARTICLE)
                .whereEqualTo(AUTHOR, name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Article article = document.toObject(Article.class);
                                Log.d("friend content", article.getArticle_content());

                            }
                        } else {
                        }
                    }
                });
    }

    public void showFriendTAGArticle(String name ,String tag) {
        db.collection(ARTICLE)
                .whereEqualTo(AUTHOR, name)
                .whereEqualTo(ARTICLE_TAG,tag)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Article article = document.toObject(Article.class);
                                Log.d("friend tag content", article.getArticle_content());

                            }
                        } else {
                        }
                    }
                });
    }

    public void showTotalArticle() {
        db.collection(ARTICLE)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Article article = document.toObject(Article.class);
                                Log.d("total content", article.getArticle_content());

                            }
                        } else {
                        }
                    }
                });
    }

    public void showTAGArticle(String tag) {
        db.collection(ARTICLE)
                .whereEqualTo(ARTICLE_TAG, tag)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Article article = document.toObject(Article.class);
                                Log.d( "tag content", article.getArticle_content());
                            }
                        } else {
                        }
                    }
                });
    }
}
