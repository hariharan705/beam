package com.hungster.utils;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.Validation;

    public interface MyOptions extends PipelineOptions {
        @Description("Path of the file to read from (e.g. gs://bucket/input/data.txt)")
        @Validation.Required
        String getInput();
        void setInput(String value);

        @Description("Path prefix to write results to (e.g. gs://bucket/output/result)")
        @Validation.Required
        String getOutput();
        void setOutput(String value);
    }
