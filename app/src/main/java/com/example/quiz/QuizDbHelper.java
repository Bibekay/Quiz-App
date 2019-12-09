package com.example.quiz;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "QUiz.db";
    private static  final int DATABASE_VERSION = 1;

    private  SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final  String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER " +

                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void    fillQuestionsTable(){
        Question q1 = new Question("What is the Height of Mount Everest ?", "8848M", "8448M",  "8845M",  1);
        addQuestion(q1);

        Question q2 = new Question("Which of these is called city of Temples?", "Bhaktapur", "Kathmandu",  "Lalitpur",  2);
        addQuestion(q2);

        Question q3 = new Question("Buddha was born in ?", "India", "China",  "Nepal",  3);
        addQuestion(q3);

        Question q4 = new Question("The longest river of Nepal ?", "Bagmati", "Koshi",  "Karnali",  3);
        addQuestion(q4);

        Question q5 = new Question("Which of these is the famous for Apple production ?", "Humla", "Jumla",  "Rukum",  2);
        addQuestion(q5);

        Question q6 = new Question("When did Krishna Prasad Bhattarai, a founder leader of the Nepali Congress and former Prime Right Minister pass away?", "20 Magh, 2057", "20 Mangshir, 2067",  "20 Falgun, 2067",  3);
        addQuestion(q6);

        Question q7 = new Question("Which is the international sports competitions that is held only in Nepal?", "Elephent Polo", "Volleyball",  "Football",  1);
        addQuestion(q7);

        Question q8 = new Question("What place is known as the Cherapunji of Nepal ?", "Kathmandu", "Surkhet",  "Pokhara",  3);
        addQuestion(q8);

        Question q9 = new Question("Which place is known as the desert of Nepal ?", "Jajarkot", "Mustang",  "Doti",  2);
        addQuestion(q9);

        Question q10 = new Question("Which mountain is understood by ‘Killer Mountain ?", "Mt Manaslu", "Mt Everest",  "Mt Annapurna",  1);
        addQuestion(q10);







    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr((c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR))));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;


    }
}
