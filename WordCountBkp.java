import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.util.HashMap;
import java.util.Map;

public class WordCount {

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    private static Map<String,String> streamMap = new HashMap<String,String>();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {

      streamMap.put("10", "10");
      streamMap.put("11", "71");
      streamMap.put("12", "82");
      streamMap.put("13", "23");
      streamMap.put("14", "54");
      streamMap.put("15", "45");
      streamMap.put("16", "66");
      streamMap.put("17", "37");
      streamMap.put("18", "98");
      streamMap.put("20", "20");
      streamMap.put("21", "81");
      streamMap.put("22", "92");
      streamMap.put("23", "33");
      streamMap.put("24", "64");
      streamMap.put("25", "55");
      streamMap.put("26", "46");
      streamMap.put("27", "17");
      streamMap.put("28", "78");
      streamMap.put("30", "30");
      streamMap.put("31", "91");
      streamMap.put("32", "72");
      streamMap.put("33", "13");
      streamMap.put("34", "44");
      streamMap.put("35", "65");
      streamMap.put("36", "56");
      streamMap.put("37", "27");
      streamMap.put("38", "88");
      streamMap.put("40", "40");
      streamMap.put("41", "11");
      streamMap.put("42", "22");
      streamMap.put("43", "53");
      streamMap.put("44", "84");
      streamMap.put("45", "75");
      streamMap.put("46", "96");
      streamMap.put("47", "67");
      streamMap.put("48", "38");
      streamMap.put("50", "50");
      streamMap.put("51", "21");
      streamMap.put("52", "32");
      streamMap.put("53", "63");
      streamMap.put("54", "94");
      streamMap.put("55", "85");
      streamMap.put("56", "76");
      streamMap.put("57", "47");
      streamMap.put("58", "18");
      streamMap.put("60", "60");
      streamMap.put("61", "31");
      streamMap.put("62", "12");
      streamMap.put("63", "43");
      streamMap.put("64", "74");
      streamMap.put("65", "95");
      streamMap.put("66", "86");
      streamMap.put("67", "57");
      streamMap.put("68", "28");
      streamMap.put("70", "70");
      streamMap.put("71", "41");
      streamMap.put("72", "52");
      streamMap.put("73", "83");
      streamMap.put("74", "24");
      streamMap.put("75", "15");
      streamMap.put("76", "36");
      streamMap.put("77", "97");
      streamMap.put("78", "68");
      streamMap.put("80", "80");
      streamMap.put("81", "51");
      streamMap.put("82", "62");
      streamMap.put("83", "93");
      streamMap.put("84", "34");
      streamMap.put("85", "25");
      streamMap.put("86", "16");
      streamMap.put("87", "77");
      streamMap.put("88", "48");
      streamMap.put("90", "90");
      streamMap.put("91", "61");
      streamMap.put("92", "42");
      streamMap.put("93", "73");
      streamMap.put("94", "14");
      streamMap.put("95", "35");
      streamMap.put("96", "26");
      streamMap.put("97", "87");
      streamMap.put("98", "58");


      StringTokenizer itr = new StringTokenizer(value.toString());
      //while (itr.hasMoreTokens()) {
        //String fi = value.toString().split(" ")[1];
        //String idDirection = value.toString().split("\t")[0];
        word.set(streamMap.get(itr.nextToken()));
        IntWritable one = new IntWritable(Integer.parseInt(itr.nextToken()));

        context.write(word, one);

      //}
    }
  }

  public static class IntSumReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        //sum = val.get();
        sum += Integer.parseInt(key.toString());
      }

      result.set(sum);
      context.write(key, result);
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "word count");
    job.setJarByClass(WordCount.class);
    job.setMapperClass(TokenizerMapper.class);
    //job.setCombinerClass(IntSumReducer.class);
    //job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
