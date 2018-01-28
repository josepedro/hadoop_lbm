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
       extends Mapper<Object, Text, Text, Text>{

    private Text id = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {

      StringTokenizer itr = new StringTokenizer(value.toString());
      id.set(itr.nextToken());
      while (itr.hasMoreTokens()) {
        String[] directionFi = itr.nextToken().split(":"); 
        String valueDir = directionFi[0] + ":" + directionFi[1];
        Text valueDirText = new Text(valueDir);
        context.write(id, valueDirText);
      }
    }
  }

  public static class IntSumReducer
       extends Reducer<Text,Text,Text,Text> {

    public void reduce(Text key, Iterable<Text> values,
                       Context context
                       ) throws IOException, InterruptedException {
      String ditributionsFunctions = "";
      for (Text val : values) {
        //sum = val.get();
        ditributionsFunctions += " " + val.toString();
      }
      Text ditributionsFunctionsText = new Text(ditributionsFunctions);
      context.write(key, ditributionsFunctionsText);
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "word count");
    job.setJarByClass(WordCount.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
