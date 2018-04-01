package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.sharing.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.sharings.SharingQuestion;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.sharings.SharingQuestionDataSource;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SharingFragmentQuestions} interface
 * to handle interaction events.
 * Use the {@link SharingFragmentQuestions#} factory method to
 * create an instance of this fragment.
 */
public class SharingFragmentQuestions extends Fragment {

    List<SharingQuestion> questions = new ArrayList<> (  );

    private SharingQuestionDataSource dataSource;

    public void populateSharedQuestions() {
        dataSource = new SharingQuestionDataSource (this.getContext());
        dataSource.open();
        questions = new ArrayList<> ( dataSource.getAllQuestions () );
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final   Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_sharing_questions, container, false);

        final TextView addQuestion = rootView.findViewById ( R.id.add_question );

        final LinearLayout custom_question_block = rootView.findViewById ( R.id.custom_question_block);
        final LinearLayout list_question_block = rootView.findViewById ( R.id.list_question_block);
        loadQuestions ( list_question_block, inflater,custom_question_block );
        addQuestion.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                custom_question_block.setVisibility ( View.VISIBLE);
                final LinearLayout inflate = (LinearLayout)inflater.inflate (
                        R.layout.add_question, container, false );
                final LinearLayout addQuestionBlock = rootView.findViewById ( R.id.add_question_block );
                final  TextView questionTextView = (TextView ) inflate.findViewById ( R.id.question_text_field);
                ImageButton saveQuestionButton = (ImageButton) inflate.findViewById ( R.id.save_question );
                saveQuestionButton.setImageResource ( R.drawable.ic_add_circle_black_24dp );
                saveQuestionButton.setBackgroundColor ( Color.TRANSPARENT );
                saveQuestionButton.setOnClickListener ( new View.OnClickListener ( ) {
                    @Override
                    public void onClick ( View v ) {
                        addQuestionBlock.removeView ( inflate );
                        saveQuestion(questionTextView.getText ().toString ());
                        loadQuestions (list_question_block,inflater,custom_question_block);

                    }
                } );

                addQuestionBlock.addView ( inflate );
            }
        } );

        return rootView;
    }

    /**
     * Loads custom questions
     */
    private void loadQuestions ( final LinearLayout parentView, final LayoutInflater inflater, final View custom_question_block) {

        parentView.removeAllViews ();
        populateSharedQuestions ();
        for (final SharingQuestion question:questions) {


            final LinearLayout inflate = (LinearLayout)inflater.inflate (
                    R.layout.custom_question, parentView, false );
            inflate.setMinimumHeight ( 30 );
            final TextView questionText= inflate.findViewById ( R.id.question_text );
            ImageButton deleteButton= inflate.findViewById ( R.id.delete_question);
            deleteButton.setImageResource ( R.drawable.ic_delete_black_24dp );
            deleteButton.setBackgroundColor ( Color.TRANSPARENT );
            deleteButton.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick ( View v ) {
                    delete(question);
                    parentView.removeView ( inflate );
                    inflate.destroyDrawingCache ();
                    loadQuestions ( parentView, inflater,custom_question_block );

                }
            } );
            questionText.setText ("- "+ question.getQuestion () );
            // Todo que faut il faire
            parentView.addView ( inflate );
        }
        manageCustomBlockVisibility ( custom_question_block );
    }


    private void manageCustomBlockVisibility(View view){
        if(questions.size ()<1 &&view!=null){

            view.setVisibility ( View.INVISIBLE );
        }
    }

    /**
     * Delete the question from db
     */
    private void delete (SharingQuestion question) {
        dataSource = new SharingQuestionDataSource (this.getContext());
        dataSource.open();

        dataSource.deleteQuestionById ( question.getId () );
        questions.remove (question);
    }

    /**
     * Saves the custom question into the database
     */
    private void saveQuestion (String text) {
        dataSource = new SharingQuestionDataSource (this.getContext());
        dataSource.open();
        SharingQuestion question = dataSource.createShareQuestion ( text );
        questions.add ( question );
    }
}
