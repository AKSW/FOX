package org.aksw.fox.tools.ner.ger;

import java.util.Properties;

import org.aksw.fox.data.Entity;
import org.aksw.fox.data.EntityClassMap;
import org.aksw.fox.tools.ner.StanfordTool;
import org.aksw.fox.utils.FoxCfg;
import org.aksw.fox.utils.FoxConst;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author rspeck
 * 
 */
public class NERStanfordDE extends StanfordTool {
    // https://github.com/stanfordnlp/CoreNLP/blob/master/src/edu/stanford/nlp/pipeline/StanfordCoreNLP-german.properties

    private static Properties props = new Properties();
    static {
        /*  props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse");*/
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
        props.setProperty("tokenize.language", "de");
        props.setProperty("pos.model", "data/stanford/models/german-hgc.tagger");
        props.setProperty("ner.model", "data/stanford/models/hgc_175m_600.crf.ser.gz");
        props.setProperty("ner.applyNumericClassifiers", "false");
        props.setProperty("ner.useSUTime", "false");
        /* props.setProperty("parse.model", "edu/stanford/nlp/models/lexparser/germanFactored.ser.gz");*/
    }

    public NERStanfordDE() {
        super(props);

        entityClasses.put("I-ORG", EntityClassMap.O);
        entityClasses.put("I-LOC", EntityClassMap.L);
        entityClasses.put("I-PER", EntityClassMap.P);
        entityClasses.put("O", EntityClassMap.N);
        entityClasses.put("I-MISC", EntityClassMap.N);
    }

    public static void main(String[] a) {
        PropertyConfigurator.configure(FoxCfg.LOG_FILE);
        for (Entity e : new NERStanfordDE().retrieve(FoxConst.NER_GER_EXAMPLE_1))
            NERBalieDE.LOG.info(e);
    }
}
