public class MemoryActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView current_result, best_result;
    private ImageView etalon;
    private ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    private Integer[] images = new Integer[]{R.drawable.airplane, R.drawable.arch, R.drawable.baby_pacifier_outline,
            R.drawable.ballons, R.drawable.boomerang, R.drawable.diploma_and_hat_of_graduate,
            R.drawable.football_ball, R.drawable.gas_station, R.drawable.sand_clock};
    private Integer hide_res = R.drawable.multiply;

    private int correctNum = 0;

    private Random rnd;

    private int current_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        best_result = (TextView)findViewById(R.id.best_result);
        current_result = (TextView)findViewById(R.id.current_result);
        etalon = (ImageView)findViewById(R.id.etalon);
        btn1 = (ImageButton)findViewById(R.id.btn1);
        btn2 = (ImageButton)findViewById(R.id.btn2);
        btn3 = (ImageButton)findViewById(R.id.btn3);
        btn4 = (ImageButton)findViewById(R.id.btn4);
        btn5 = (ImageButton)findViewById(R.id.btn5);
        btn6 = (ImageButton)findViewById(R.id.btn6);
        btn7 = (ImageButton)findViewById(R.id.btn7);
        btn8 = (ImageButton)findViewById(R.id.btn8);
        btn9 = (ImageButton)findViewById(R.id.btn9);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        rnd = new Random();
        loadLevel();
        current_count = 0;
        SharedPreferences preferences = getSharedPreferences(GamingActivity.KEY_PREFERENCES, Context.MODE_PRIVATE);
        LocalStorage.getInstance().saveResult(preferences, current_count, GamingActivity.KEY_BEST_MEMORY);
        best_result.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_MEMORY));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                if (correctNum == 0){
                    result(false);
                }else {
                    result(true);
                }
                break;
            case R.id.btn2:
                if (correctNum == 1){
                    result(false);
                }else {
                    result(true);
                }
                break;
            case R.id.btn3:
                if (correctNum == 2){
                    result(false);
                }else {
                    result(true);
                }
                break;
            case R.id.btn4:
                if (correctNum == 3){
                    result(false);
                }else {
                    result(true);
                }
                break;
            case R.id.btn5:
                if (correctNum == 4){
                    result(false);
                }else {
                    result(true);
                }
                break;
            case R.id.btn6:
                if (correctNum == 5){
                    result(false);
                }else {
                    result(true);
                }
                break;
            case R.id.btn7:
                if (correctNum == 6){
                    result(false);
                }else {
                    result(true);
                }
                break;
            case R.id.btn8:
                if (correctNum == 7){
                    result(false);
                }else {
                    result(true);
                }
                break;
            case R.id.btn9:
                if (correctNum == 8){
                    result(false);
                }else {
                    result(true);
                }
                break;
        }
    }

    public void showBtnIm(){
        btn1.setImageResource(images[0]);
        btn2.setImageResource(images[1]);
        btn3.setImageResource(images[2]);
        btn4.setImageResource(images[3]);
        btn5.setImageResource(images[4]);
        btn6.setImageResource(images[5]);
        btn7.setImageResource(images[6]);
        btn8.setImageResource(images[7]);
        btn9.setImageResource(images[8]);
    }

    public void hideBtnIm(){
        btn1.setImageResource(R.drawable.multiply);
        btn2.setImageResource(R.drawable.multiply);
        btn3.setImageResource(R.drawable.multiply);
        btn4.setImageResource(R.drawable.multiply);
        btn5.setImageResource(R.drawable.multiply);
        btn6.setImageResource(R.drawable.multiply);
        btn7.setImageResource(R.drawable.multiply);
        btn8.setImageResource(R.drawable.multiply);
        btn9.setImageResource(R.drawable.multiply);
    }

    public void loadLevel(){
        Collections.shuffle(Arrays.asList(images));
        correctNum = rnd.nextInt(9);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                etalon.setImageResource(images[correctNum]);
                hideBtnIm();
            }
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        etalon.setImageResource(R.drawable.multiply);
                        showBtnIm();
                    }
                });
            }
        });
        t.start();
    }

    public void result(boolean game_over){
        if (game_over){
            Toast.makeText(this, "you not guess", Toast.LENGTH_SHORT).show();
            showGameOverDialog("гра завершена, ви отримали " + String.valueOf(current_count));

        }else {
            Toast.makeText(this, "вгадай", Toast.LENGTH_SHORT).show();
            current_count++;
            current_result.setText("поточний результат " + String.valueOf(current_count));
            SharedPreferences preferences = getSharedPreferences(GamingActivity.KEY_PREFERENCES, Context.MODE_PRIVATE);
            LocalStorage.getInstance().saveResult(preferences, current_count, GamingActivity.KEY_BEST_MEMORY);
            best_result.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_MEMORY));
            Thread t = new Thread(){
                @Override
                public void run() {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    loadLevel();
                }
            };
            t.start();
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
                        loadLevel();
                    }
                });
        builder.create().show();
    }
}
