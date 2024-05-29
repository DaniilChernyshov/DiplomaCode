public class GamingActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KEY_PREFERENCES = "KEY_PREFERENCES";
    public static final String KEY_BEST_SUM = "KEY_BEST_SUM";
    public static final String KEY_BEST_MEMORY = "KEY_BEST_MEMORY";
    public static final String KEY_BEST_ANIMAL = "KEY_BEST_ANIMAL";
    public static final String KEY_BEST_WORD = "KEY_BEST_WORD";

    private TextView best_sum, best_memory, best_animal, best_word;
    private ImageButton sum_butt, memory_butt, animal_butt, word_butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaming);

        best_sum = (TextView)findViewById(R.id.best_sum);
        best_memory = (TextView)findViewById(R.id.best_memory);
        best_animal = (TextView)findViewById(R.id.best_animal);
        best_word = (TextView)findViewById(R.id.best_word);

        sum_butt = (ImageButton)findViewById(R.id.sum_butt);
        memory_butt = (ImageButton)findViewById(R.id.memory_butt);
        animal_butt = (ImageButton)findViewById(R.id.animal_butt);
        word_butt = (ImageButton)findViewById(R.id.word_butt);

        sum_butt.setOnClickListener(this);
        memory_butt.setOnClickListener(this);
        animal_butt.setOnClickListener(this);
        word_butt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.sum_butt:
                intent = new Intent(this, SumActivity.class);
                break;
            case R.id.memory_butt:
                intent = new Intent(this, MemoryActivity.class);
                break;
            case R.id.animal_butt:
                intent = new Intent(this, AnimalActivity.class);
                break;
            case R.id.word_butt:
                intent = new Intent(this, WordActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE);
        best_sum.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_SUM));
        best_memory.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_MEMORY));
        best_animal.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_ANIMAL));
        best_word.setText("кращий результат " + LocalStorage.getInstance().getBestResult(preferences, GamingActivity.KEY_BEST_WORD));
    }
}
