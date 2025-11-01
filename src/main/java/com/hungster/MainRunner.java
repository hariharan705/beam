package com.hungster;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.TypeDescriptors;
import com.hungster.utils.MyOptions;

import myParDo.extractFirstCol;

import org.apache.beam.sdk.values.PCollection;



public class MainRunner {

    public static String apply(String x){
        return x.toUpperCase();
    }
    public static void main(String[] args) {

      // creating pipeline options
        MyOptions options = PipelineOptionsFactory.fromArgs(args)
                            .withValidation().as(MyOptions.class);
        // creating pipeline
        Pipeline p=Pipeline.create(options);

       PCollection<String> lines=p.apply("read from the given path",TextIO.read().from(options.getInput()));

       PCollection<String> upperCase=lines.apply("transform to uppercase",MapElements.into(TypeDescriptors.strings()).via(MainRunner::apply));
       PCollection<String> firstCol=upperCase.apply("extract first column",ParDo.of(new myParDo.extractFirstCol()));
       firstCol.apply("write to the given path",TextIO.write().to(options.getOutput())
        .withNumShards(1).withSuffix(".txt")) ;


      








       p.run().waitUntilFinish();
        
}
}