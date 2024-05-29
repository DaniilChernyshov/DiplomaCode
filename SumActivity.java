public class SumActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView number1, number2, current_result, best_result;
    private Button butt1, butt2, butt3;

    private Random random;

    private int coorrect_result;
    private int current_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);

        number1 = (TextView)findViewById(R.id.number1);
        number2 = (TextView)findViewById(R.id.number2);
        current_result = (TextView)findViewById(R.id.current_result);
        best_result = (TextView)findViewById(R.id.best_result);

        butt1 = (Button)findViewById(R.id.butt1);
        butt2 = (Button)findViewById(R.id.butt2);
        butt3 = (Button)findViewById(R.id.butt3);

        butt1.setOnClickListener(this);
        butt2.setOnClickListener(this);
        butt3.setOnClickListener(this);

        random = new Random();
        current_count = 0;
        SharedPreferences preferences = getSharedPreferences(GamingActivity.KEY_PREFERENCES, Context.MODE_PRIVATE);
        LocalStorage.getInstance().saveResult(preferences, current_count, GamingActivity.KEY_BEST_SUM);
        best_result.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_SUM));
        startGame();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.butt1:
                clickButt(butt1.getText().toString());
                break;
            case R.id.butt2:
                clickButt(butt2.getText().toString());
                break;
            case R.id.butt3:
                clickButt(butt3.getText().toString());
                break;
        }
    }

    public void startGame(){
        int n1 = random.nextInt(10);
        int n2 = random.nextInt(10);
        coorrect_result = n1 + n2;
        
	int pos = random.nextInt(3);
        
	int nocor1 = coorrect_result;
        
	while (nocor1 == coorrect_result){
            nocor1 = random.nextInt(19);
        }
        int nocor2 = coorrect_result;
        while (nocor2 == coorrect_result || nocor2 == nocor1){
            nocor2 = random.nextInt(19);
        }
        switch (pos){
            case 0:
                butt1.setText(String.valueOf(coorrect_result));
                butt2.setText(String.valueOf(nocor1));
                butt3.setText(String.valueOf(nocor2));
                break;
            case 1:
                butt2.setText(String.valueOf(coorrect_result));
                butt1.setText(String.valueOf(nocor1));
                butt3.setText(String.valueOf(nocor2));
                break;
            case 2:
                butt3.setText(String.valueOf(coorrect_result));
                butt2.setText(String.valueOf(nocor1));
                butt1.setText(String.valueOf(nocor2));
                break;
        }
        number1.setText(String.valueOf(n1));
        number2.setText(String.valueOf(n2));

    }

    public void clickButt(String textButt){
        if (textButt.equals(String.valueOf(coorrect_result))){
            current_count++;
            current_result.setText("кращий результат " + String.valueOf(current_count));
            SharedPreferences preferences = getSharedPreferences(GamingActivity.KEY_PREFERENCES, Context.MODE_PRIVATE);
            LocalStorage.getInstance().saveResult(preferences, current_count, GamingActivity.KEY_BEST_SUM);
            best_result.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_SUM));
            startGame();
        }else {
            showGameOverDialog("гра завершена, ви отримали " + String.valueOf(current_count));
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
