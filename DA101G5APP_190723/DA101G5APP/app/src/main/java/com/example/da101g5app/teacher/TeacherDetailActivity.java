package com.example.da101g5app.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.da101g5app.R;
import com.example.da101g5app.task.ImageTask;
import com.example.da101g5app.util.Util;

public class TeacherDetailActivity extends AppCompatActivity {
    private MediaController mMediaController;
    private VideoView videoView;
    private ImageTask getTeacher_picTask;
    private int imageSize;

    private ImageView teacher_pic;
    private RatingBar teacher_star;
    private TextView teacher_name;
    private TextView teacher_language;
    private TextView teacher_country;
    private TextView teacher_price;
    private TextView teacher_nick;
    private TextView accum;
    private TextView appra_count;

    private TextView ed_background;
    private TextView cert;
    private TextView work_experience;
    private TextView introduce;
    private Button atd_buy_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_detail);

        findView();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        TeacherCardVO teacherCardVO = (TeacherCardVO) bundle.getSerializable("teacherCardVO");
        if(teacherCardVO == null){
            teacherCardVO = (TeacherCardVO) bundle.getSerializable("myTeacherCardVO");
        }

        String url = Util.URL + "TeacherServletApp";
        int imageSize = imageSize = getResources().getDisplayMetrics().widthPixels / 4;
        getTeacher_picTask = new ImageTask(url, teacherCardVO.getTeacher_id(), imageSize, teacher_pic);
        getTeacher_picTask.execute();

        teacher_star.setRating(Float.valueOf(teacherCardVO.getAppraisal_accum()) / Float.valueOf(teacherCardVO.getAppraisal_count()));
        teacher_name.setText(teacherCardVO.getMem_name());
        teacher_country.setText(teacherCardVO.getMem_country());
        teacher_language.setText(teacherCardVO.getLanguage());
        teacher_price.setText(String.valueOf(teacherCardVO.getCourse_price()));
        teacher_nick.setText(teacherCardVO.getMem_nick());
        ed_background.setText(teacherCardVO.getEd_background());
        cert.setText(teacherCardVO.getCertification());
        work_experience.setText(teacherCardVO.getWork_experience());
        introduce.setText(teacherCardVO.getTeacher_introduce());
        accum.setText(String.valueOf(teacherCardVO.getAppraisal_accum()));
        appra_count.setText(String.valueOf(teacherCardVO.getAppraisal_count()));


        atd_buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomSheetDialogFragment bottomSheetDialogFragment = new TeacherDetailBottomFragment();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());

            }
        });


    }

    public void findView(){
          teacher_pic = findViewById(R.id.atd_teacher_pic);
          teacher_star = findViewById(R.id.atd_teacher_star);
          teacher_name = findViewById(R.id.atd_teacher_name);
          teacher_language = findViewById(R.id.atd_teacher_language);
          teacher_country = findViewById(R.id.atd_teacher_country);
          teacher_price = findViewById(R.id.atd_teacher_price);
          teacher_nick = findViewById(R.id.atd_teacher_nick);

          ed_background = findViewById(R.id.atd_teacher_ed_background);
          cert = findViewById(R.id.atd_teacher_cert);
          work_experience = findViewById(R.id.atd_teacher_work_ex);
          introduce = findViewById(R.id.atd_teacher_intro);

          atd_buy_btn = findViewById(R.id.atd_buy_btn);

          accum = findViewById(R.id.act_teacher_detail_accum);
          appra_count = findViewById(R.id.act_teacher_detail_apprasal_count);
    }
}
