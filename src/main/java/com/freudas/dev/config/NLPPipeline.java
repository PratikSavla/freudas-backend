package com.freudas.dev.config;
////nlpPipeline.java
//import java.util.Properties;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import edu.stanford.nlp.ling.CoreAnnotations;
//import edu.stanford.nlp.pipeline.Annotation;
//import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
//import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
//import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
//import edu.stanford.nlp.trees.Tree;
//import edu.stanford.nlp.util.CoreMap;
//import org.springframework.context.annotation.Configuration;

//@Configuration
public class NLPPipeline {
//    private final StanfordCoreNLP pipeline;
//    public NLPPipeline()
//    {
//        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
//        pipeline = new StanfordCoreNLP(props);
//    }
//    public double estimatingSentiment(String text)
//    {
//        Double sentimentInt = 0.0;
//        String sentimentName;
//        int sentenceCount = 0;
//        Annotation annotation = pipeline.process(text);
//        for(CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class))
//        {
//            Tree tree = sentence.get(SentimentAnnotatedTree.class);
//            sentimentInt += RNNCoreAnnotations.getPredictedClass(tree);
//            sentimentName = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
//            sentenceCount++;
//            System.out.println(sentimentName + "\t" + sentimentInt + "\t" + sentence);
//        }
//        return sentimentInt/sentenceCount;
//    }
}