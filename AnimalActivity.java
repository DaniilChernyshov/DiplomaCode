public class AnimalActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView current_result, best_result;
    private ImageView animal_view;
    private Button butt1, butt2, butt3;

    private Map<Integer, String>  map;

    private Integer correctNum = 0;

    private Random rnd;

    private int current_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        best_result = (TextView)findViewById(R.id.best_result);
        current_result = (TextView)findViewById(R.id.current_result);
        animal_view = (ImageView)findViewById(R.id.animal_view);
        butt1 = (Button)findViewById(R.id.butt1);
        butt2 = (Button)findViewById(R.id.butt2);
        butt3 = (Button)findViewById(R.id.butt3);
        butt1.setOnClickListener(this);
        butt2.setOnClickListener(this);
        butt3.setOnClickListener(this);

        rnd = new Random();
        current_count = 0;
        SharedPreferences preferences = getSharedPreferences(GamingActivity.KEY_PREFERENCES, Context.MODE_PRIVATE);
        LocalStorage.getInstance().saveResult(preferences, current_count, GamingActivity.KEY_BEST_ANIMAL);
        best_result.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_ANIMAL));

        map = new HashMap<>();
        map.put(R.drawable.cat, "кіт");
        map.put(R.drawable.bulldog, "собака");
        map.put(R.drawable.cow, "корова");
        map.put(R.drawable.elephant, "слон");
        map.put(R.drawable.hippopotamus, "бегемот");
        map.put(R.drawable.koala, "коала");
        map.put(R.drawable.llama, "овечка");
        map.put(R.drawable.lobster, "краб");
        map.put(R.drawable.mouse, "ведмедик");
        map.put(R.drawable.squirrel, "білка");
        startGame();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.butt1:
                if (butt1.getText().equals(map.get(correctNum))){
                    current_count++;
                    current_result.setText("поточний результат " + String.valueOf(current_count));
                    SharedPreferences preferences = getSharedPreferences(GamingActivity.KEY_PREFERENCES, Context.MODE_PRIVATE);
                    LocalStorage.getInstance().saveResult(preferences, current_count, GamingActivity.KEY_BEST_ANIMAL);
                    best_result.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_ANIMAL));
                    startGame();
                }else {
                    showGameOverDialog("гра завершена, ви отримали " + String.valueOf(current_count));
                }
                break;
            case R.id.butt2:
                if (butt2.getText().equals(map.get(correctNum))){
                    current_count++;
                    current_result.setText("поточний результат " + String.valueOf(current_count));
                    SharedPreferences preferences = getSharedPreferences(GamingActivity.KEY_PREFERENCES, Context.MODE_PRIVATE);
                    LocalStorage.getInstance().saveResult(preferences, current_count, GamingActivity.KEY_BEST_ANIMAL);
                    best_result.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_ANIMAL));
                    startGame();
                }else {
                    showGameOverDialog("гра завершена, ви отримали " + String.valueOf(current_count));
                }
                break;
            case R.id.butt3:
                if (butt3.getText().equals(map.get(correctNum))){
                    current_count++;
                    current_result.setText("поточний результат " + String.valueOf(current_count));
                    SharedPreferences preferences = getSharedPreferences(GamingActivity.KEY_PREFERENCES, Context.MODE_PRIVATE);
                    LocalStorage.getInstance().saveResult(preferences, current_count, GamingActivity.KEY_BEST_ANIMAL);
                    best_result.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_ANIMAL));
                    startGame();
                }else {
                    showGameOverDialog("гра завершена, ваші бали " + String.valueOf(current_count));
                }
                break;
        }
    }

    public void startGame(){
        int r = rnd.nextInt(map.size());
        correctNum = map.keySet().toArray(new Integer[map.size()])[r];
        int pos = rnd.nextInt(3);
        int nocor1 = r;
        while (nocor1 == r){
            nocor1 = rnd.nextInt(map.size());
        }
        int nocor2 = r;
        while (nocor2 == r || nocor2 == nocor1){
            nocor2 = rnd.nextInt(map.size());
        }
        switch (pos){
            case 0:
                butt1.setText(map.get(correctNum));
                butt2.setText(map.get(map.keySet().toArray(new Integer[map.size()])[nocor1]));
                butt3.setText(map.get(map.keySet().toArray(new Integer[map.size()])[nocor2]));
                break;
            case 1:
                butt2.setText(map.get(correctNum));
                butt1.setText(map.get(map.keySet().toArray(new Integer[map.size()])[nocor1]));
                butt3.setText(map.get(map.keySet().toArray(new Integer[map.size()])[nocor2]));
                break;
            case 2:
                butt3.setText(map.get(correctNum));
                butt2.setText(map.get(map.keySet().toArray(new Integer[map.size()])[nocor1]));
                butt1.setText(map.get(map.keySet().toArray(new Integer[map.size()])[nocor2]));
                break;
        }
        animal_view.setImageResource(correctNum);
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
