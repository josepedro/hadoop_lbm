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

      streamMap.put("01", "01");
      streamMap.put("11", "17");
      streamMap.put("21", "28");
      streamMap.put("31", "32");
      streamMap.put("41", "45");
      streamMap.put("51", "54");
      streamMap.put("61", "66");
      streamMap.put("71", "73");
      streamMap.put("81", "89");
      streamMap.put("02", "02");
      streamMap.put("12", "18");
      streamMap.put("22", "29");
      streamMap.put("32", "33");
      streamMap.put("42", "46");
      streamMap.put("52", "55");
      streamMap.put("62", "64");
      streamMap.put("72", "71");
      streamMap.put("82", "87");
      streamMap.put("03", "03");
      streamMap.put("13", "19");
      streamMap.put("23", "27");
      streamMap.put("33", "31");
      streamMap.put("43", "44");
      streamMap.put("53", "56");
      streamMap.put("63", "65");
      streamMap.put("73", "72");
      streamMap.put("83", "88");
      streamMap.put("04", "04");
      streamMap.put("14", "11");
      streamMap.put("24", "22");
      streamMap.put("34", "35");
      streamMap.put("44", "48");
      streamMap.put("54", "57");
      streamMap.put("64", "69");
      streamMap.put("74", "76");
      streamMap.put("84", "83");
      streamMap.put("05", "05");
      streamMap.put("15", "12");
      streamMap.put("25", "23");
      streamMap.put("35", "36");
      streamMap.put("45", "49");
      streamMap.put("55", "58");
      streamMap.put("65", "67");
      streamMap.put("75", "74");
      streamMap.put("85", "81");
      streamMap.put("06", "06");
      streamMap.put("16", "13");
      streamMap.put("26", "21");
      streamMap.put("36", "34");
      streamMap.put("46", "47");
      streamMap.put("56", "59");
      streamMap.put("66", "68");
      streamMap.put("76", "75");
      streamMap.put("86", "82");
      streamMap.put("07", "07");
      streamMap.put("17", "14");
      streamMap.put("27", "25");
      streamMap.put("37", "38");
      streamMap.put("47", "42");
      streamMap.put("57", "51");
      streamMap.put("67", "63");
      streamMap.put("77", "79");
      streamMap.put("87", "86");
      streamMap.put("08", "08");
      streamMap.put("18", "15");
      streamMap.put("28", "26");
      streamMap.put("38", "39");
      streamMap.put("48", "43");
      streamMap.put("58", "52");
      streamMap.put("68", "61");
      streamMap.put("78", "77");
      streamMap.put("88", "84");
      streamMap.put("09", "09");
      streamMap.put("19", "16");
      streamMap.put("29", "24");
      streamMap.put("39", "37");
      streamMap.put("49", "41");
      streamMap.put("59", "53");
      streamMap.put("69", "62");
      streamMap.put("79", "78");
      streamMap.put("89", "85");


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
