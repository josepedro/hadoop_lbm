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

    public static int number_lines;
    public static int number_rows;

    public static int[] getIdPosition(int id, int number_lines, int number_rows){
        int idCount = 1;
        for (int line = 0; line < number_lines; line++) {
            for (int row = 0; row < number_rows; row++) {
                if (idCount == id) {
                    int[] idPosition = {line, row};
                    return idPosition;
                } else {
                    idCount += 1;
                }
            }      
        }
        return null;
    }

    public static int getPositionId(int lineId, int rowId, int number_lines, int number_rows){
        int idCount = 1;
        for (int line = 0; line < number_lines; line++) {
            for (int row = 0; row < number_rows; row++) {
                if (line == lineId && row == rowId) {
                    return idCount;
                } else {
                    idCount += 1;
                }
            }      
        }
        return idCount;
    }
    
    public static int[] get1(int line, int row, int number_lines, int number_rows){
        if (line == 0 && row == 0){
            int[] result = {number_lines - 1, row};
            return result;
        }
        if (line == 0 && row == number_rows - 1){
            int[] result = {number_lines - 1, number_rows - 1};
            return result;
        }
        if (line == 0 && row > 0 && row < number_rows - 1){
            int[] result = {number_lines - 1, row};
            return result;
        }
        else{
            int[] result = {line - 1, row};
            return result;
        }
    }

    public static int get1_id(int id, int number_lines, int number_rows){
        int[] position_id = getIdPosition(id, number_lines, number_rows);
        int[] position_neighborhood = get1(position_id[0], position_id[1], number_lines, number_rows);
        return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows);
    }

    public static int[] get2(int line, int row, int number_lines, int number_rows){
        if (line == 0 && row == 0){
            return new int[] {number_lines - 1, row + 1};
        }
        if (line == 0 && row == number_rows - 1){
            return new int[] {number_lines - 1, 0};
        }
        if (line == number_lines - 1 && row == number_rows - 1){
            return new int[] {number_lines - 2, 0};
        }
        if (line == 0 && row > 0 && row < number_rows - 1){
            return new int[] {number_lines - 1, row + 1};
        }
        if (line > 0 && line < number_lines - 1 && row == number_rows - 1){
            return new int[] {line - 1, 0};
        }
        else{
            return new int[] {line - 1, row + 1};
        }
    }

    public static int get2_id(int id, int number_lines, int number_rows){
        int[] position_id = getIdPosition(id, number_lines, number_rows);
        int[] position_neighborhood = get2(position_id[0], position_id[1], number_lines, number_rows);
        return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows);
    }

    public static int[] get3(int line, int row, int number_lines, int number_rows){
        if (line == 0 && row == number_rows - 1){
            return new int[] {line, 0};
        }
        if (line == number_lines - 1 && row == number_rows - 1){
            return new int[] {number_lines - 1, 0};
        }
        if (line > 0 && line < number_lines - 1 && row == number_rows - 1){
            return new int[] {line, 0};
        }
        else{
            return new int[] {line, row + 1};
        }   
    }
    
    public static int get3_id(int id, int number_lines, int number_rows){
        int[] position_id = getIdPosition(id, number_lines, number_rows);
        int[] position_neighborhood = get3(position_id[0], position_id[1], number_lines, number_rows);
        return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows);
    }

    public static int[] get4(int line, int row, int number_lines, int number_rows){
        if (line == 0 && row == number_rows - 1){
            return new int[] {1, 0};
        }
        if (line == number_lines - 1 && row == number_rows - 1){
            return new int[] {0, 0};
        }
        if (line == number_lines - 1 && row == 0){
            return new int[] {0, 1};
        }
        if (line > 0 && line < number_lines - 1 && row == number_rows - 1){
            return new int[] {line + 1, 0};
        }
        if (line == number_lines - 1 && row > 0 && row < number_rows - 1){
            return new int[] {0, row + 1};
        }
        else{
            return new int[] {line + 1, row + 1};
        }
    }

    public static int get4_id(int id, int number_lines, int number_rows){
        int[] position_id = getIdPosition(id, number_lines, number_rows);
        int[] position_neighborhood = get4(position_id[0], position_id[1], number_lines, number_rows);
        return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows);
    }

    public static int[] get5(int line, int row, int number_lines, int number_rows){
        if (line == number_lines - 1 && row == 0){
            return new int[] {0, row};
        }
        if (line == number_lines - 1 && row == number_rows - 1){
            return new int[] {0, row};
        }
        if (line == number_lines - 1 && row > 0 && row < number_rows - 1){
            return new int[] {0, row};
        }
        else{
            return new int[] {line + 1, row};
        }   
    }

    public static int get5_id(int id, int number_lines, int number_rows){
        int[] position_id = getIdPosition(id, number_lines, number_rows);
        int[] position_neighborhood = get5(position_id[0], position_id[1], number_lines, number_rows);
        return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows);
    }

    public static int[] get6(int line, int row, int number_lines, int number_rows){
        if (line == 0 && row == 0){
            return new int[] {line + 1, number_rows - 1};
        }
        if (line ==  number_lines - 1 && row == 0){
            return new int[] {0, number_rows - 1};
        }
        if (line == number_lines - 1 && row == number_rows - 1){
            return new int[] {0, row - 1};
        }
        if (line > 0 && line < number_lines - 1 && row == 0){
            return new int[] {line + 1, number_rows - 1};
        }
        if (line == number_lines - 1 && row > 0 && row < number_rows - 1){
            return new int[] {0, row - 1}; 
        }
        else{   
            return new int[] {line + 1, row - 1};
        }
    }

    public static int get6_id(int id, int number_lines, int number_rows){
        int[] position_id = getIdPosition(id, number_lines, number_rows);
        int[] position_neighborhood = get6(position_id[0], position_id[1], number_lines, number_rows);
        return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows);
    }

    public static int[] get7(int line, int row, int number_lines, int number_rows){
        if (line == 0 && row == 0){
            return new int[] {line, number_rows - 1};
        }
        if (line == number_lines - 1 && row == 0){
            return new int[] {line, number_rows - 1};
        }
        if (line > 0 && line < number_lines - 1 && row == 0){
            return new int[] {line, number_rows - 1};
        }
        else{
            return new int[] {line, row - 1};
        }
    }

    public static int get7_id(int id, int number_lines, int number_rows){
        int[] position_id = getIdPosition(id, number_lines, number_rows);
        int[] position_neighborhood = get7(position_id[0], position_id[1], number_lines, number_rows);
        return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows);
    }

    public static int[] get8(int line, int row, int number_lines, int number_rows){
        if (line == 0 && row == 0){
            return new int[] {number_lines - 1, number_rows - 1};
        }
        if (line == 0 && row == number_rows - 1){
            return new int[] {number_lines - 1, row - 1};
        }
        if (line == number_lines - 1 && row == 0){
            return new int[] {line - 1, number_rows - 1};
        }
        if (line > 0 && line < number_lines - 1 && row == 0){
            return new int[] {line - 1, number_rows - 1};
        }
        if (line == 0 && row > 0 && row < number_rows - 1){
            return new int[] {number_lines - 1, row - 1};
        }
        else{
            return new int[] {line - 1, row - 1};
        }
    }

    public static int get8_id(int id, int number_lines, int number_rows){
        int[] position_id = getIdPosition(id, number_lines, number_rows);
        int[] position_neighborhood = get8(position_id[0], position_id[1], number_lines, number_rows);
        return getPositionId(position_neighborhood[0], position_neighborhood[1], number_lines, number_rows);
    }

    public static class TokenizerMapper
       extends Mapper<Object, Text, Text, Text>{

        private Text id = new Text();

        public void map(Object key, Text value, Context context
                        ) throws IOException, InterruptedException {

          StringTokenizer itr = new StringTokenizer(value.toString());
          id.set(itr.nextToken());
          // for each direction
          while (itr.hasMoreTokens()) {
            String[] dirFun = itr.nextToken().split(":");
            String direction = dirFun[0];
            String ditributionFunction = dirFun[1];
            String valueDir = direction + ":" + ditributionFunction;
            Text valueDirText = new Text(valueDir);
            //String newId = streamMap.get(direction + id.toString());
            if (Integer.parseInt(direction) == 1) {
                String newId = Integer.toString(
                    get1_id(
                        Integer.parseInt(id.toString()), number_lines, number_rows));
                context.write(new Text(newId), valueDirText);
            }
            else if (Integer.parseInt(direction) == 2) {
                String newId = Integer.toString(
                    get2_id(
                        Integer.parseInt(id.toString()), number_lines, number_rows));
                context.write(new Text(newId), valueDirText);   
            }
            else if (Integer.parseInt(direction) == 3) {
                String newId = Integer.toString(
                    get3_id(
                        Integer.parseInt(id.toString()), number_lines, number_rows));
                context.write(new Text(newId), valueDirText);
            }
            else if (Integer.parseInt(direction) == 4) {
                String newId = Integer.toString(
                    get4_id(
                        Integer.parseInt(id.toString()), number_lines, number_rows));
                context.write(new Text(newId), valueDirText);
            }
            else if (Integer.parseInt(direction) == 5) {
                String newId = Integer.toString(
                    get5_id(
                        Integer.parseInt(id.toString()), number_lines, number_rows));
                context.write(new Text(newId), valueDirText);
            }
            else if (Integer.parseInt(direction) == 6) {
                String newId = Integer.toString(
                    get6_id(
                        Integer.parseInt(id.toString()), number_lines, number_rows));
                context.write(new Text(newId), valueDirText);
            }
            else if (Integer.parseInt(direction) == 7) {
                String newId = Integer.toString(
                    get7_id(
                        Integer.parseInt(id.toString()), number_lines, number_rows));
                context.write(new Text(newId), valueDirText);
            }
            else if (Integer.parseInt(direction) == 8) {
                String newId = Integer.toString(
                    get8_id(
                        Integer.parseInt(id.toString()), number_lines, number_rows));
                context.write(new Text(newId), valueDirText);
            } else {
                context.write(id, valueDirText);
            }
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

        number_lines = 3;
        number_rows = 3;

        //System.out.println("\nDEBUGAAAANDO\n");
        //System.out.println(get8_id(5,number_lines, number_rows));

        Configuration conf = new Configuration();
        boolean status = false;
        for (int i = 0; i<100; i++) {
            Job job = Job.getInstance(conf, "word count");
            job.setJarByClass(WordCount.class);
            job.setMapperClass(TokenizerMapper.class);
            job.setCombinerClass(IntSumReducer.class);
            job.setReducerClass(IntSumReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);
            String fileIn = args[0] + Integer.toString(i);
            String fileOut = args[0] + Integer.toString(i+1);
            FileInputFormat.addInputPath(job, new Path(fileIn));
            FileOutputFormat.setOutputPath(job, new Path(fileOut));
            status = job.waitForCompletion(true);     
        }
        
        System.exit(status ? 0 : 1);
    }
}
