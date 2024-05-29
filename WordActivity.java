public class WordActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView current_result, best_result, word_view;
    private Button butt1, butt2, butt3;
    private char correctSymbol;

    private Random rnd;

    private int current_count;

    private String []words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        words = getResources().getStringArray(R.array.words);
        rnd = new Random();
        current_count = 0;
        current_result = (TextView)findViewById(R.id.current_result);
        best_result = (TextView)findViewById(R.id.best_result);
        word_view = (TextView)findViewById(R.id.word_view);

        butt1 = (Button)findViewById(R.id.butt1);
        butt2 = (Button)findViewById(R.id.butt2);
        butt3 = (Button)findViewById(R.id.butt3);
        butt1.setOnClickListener(this);
        butt2.setOnClickListener(this);
        butt3.setOnClickListener(this);

        SharedPreferences preferences = getSharedPreferences(GamingActivity.KEY_PREFERENCES, Context.MODE_PRIVATE);
        LocalStorage.getInstance().saveResult(preferences, current_count, GamingActivity.KEY_BEST_WORD);
        best_result.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_WORD));

        startGame();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.butt1:
                if (butt1.getText().equals(correctSymbol + "")){
                    current_count++;
                    current_result.setText("поточний результат " + String.valueOf(current_count));
                    SharedPreferences preferences = getSharedPreferences(GamingActivity.KEY_PREFERENCES, Context.MODE_PRIVATE);
                    LocalStorage.getInstance().saveResult(preferences, current_count, GamingActivity.KEY_BEST_WORD);
                    best_result.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_WORD));
                    startGame();
                }else {
                    showGameOverDialog("гра завершена, ваші бали " + String.valueOf(current_count));
                }
                break;
            case R.id.butt2:
                if (butt2.getText().equals(correctSymbol + "")){
                    current_count++;
                    current_result.setText("кращий результат " + String.valueOf(current_count));
                    SharedPreferences preferences = getSharedPreferences(GamingActivity.KEY_PREFERENCES, Context.MODE_PRIVATE);
                    LocalStorage.getInstance().saveResult(preferences, current_count, GamingActivity.KEY_BEST_WORD);
                    best_result.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_WORD));
                    startGame();
                }else {
                    showGameOverDialog("гра завершена, ваші бали " + String.valueOf(current_count));
                }
                break;
            case R.id.butt3:
                if (butt3.getText().equals(correctSymbol + "")){
                    current_count++;
                    current_result.setText("поточний результат " + String.valueOf(current_count));
                    SharedPreferences preferences = getSharedPreferences(GamingActivity.KEY_PREFERENCES, Context.MODE_PRIVATE);
                    LocalStorage.getInstance().saveResult(preferences, current_count, GamingActivity.KEY_BEST_WORD);
                    best_result.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_WORD));
                    startGame();
                }else {
                    showGameOverDialog("гра завершена, ваші бали" + String.valueOf(current_count));
                }
                break;
        }
    }

    public void startGame(){
        int r = rnd.nextInt(words.length);
        String word = words[r];
        int t = rnd.nextInt(word.length());
        StringBuilder temp = new StringBuilder();
        for (int i = 0;i < word.length();i++){
            if (i == t){
                temp.append("?");
            }else {
                temp.append(word.charAt(i));
            }
        }
        word_view.setText(temp.toString());
        correctSymbol = word.charAt(t);
        int pos = rnd.nextInt(3);
        int nocor1 = correctSymbol;
        while (nocor1 == correctSymbol){
            nocor1 = 'а' + rnd.nextInt((int)'я' - (int) 'а');
        }
        int nocor2 = correctSymbol;
        while (nocor2 == correctSymbol || nocor2 == nocor1){
            nocor2 = 'а' + rnd.nextInt((int)'я' - (int) 'а');
        }
        switch (pos){
            case 0:
                butt1.setText(correctSymbol + "");
                butt2.setText((char)nocor1 + "");
                butt3.setText((char)nocor2 + "");
                break;
            case 1:
                butt2.setText(correctSymbol + "");
                butt1.setText((char)nocor1 + "");
                butt3.setText((char)nocor2 + "");
                break;
            case 2:
                butt3.setText(correctSymbol + "");
                butt2.setText((char)nocor1 + "");
                butt1.setText((char)nocor2 + "");
                break;
        }
    }

    public void showGameOverDialog(String text){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setMessage(text)
                .setNeutralButton("Почати спочатку", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        current_count = 0;
                        current_result.setText("поточний результат " + String.valueOf(current_count));
                        startGame();
                    }
                });
        builder.create().show();
    }
}
