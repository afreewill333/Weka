import java.util.*;
import java.io.*;

import weka.associations.Apriori;
import weka.core.*;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.Evaluation;


public class WekaApriori {

	public static void main(String[] args) throws Exception{

		 
		 // Declare a nominal attribute along with its values
		 ArrayList<String> nominals = new ArrayList<String>(3);
		 nominals.add("blue");
		 nominals.add("gray");
		 nominals.add("black");
		 Attribute attr3 = new Attribute("color", nominals);
		 
		 // Declare the class attribute along with its values
		 ArrayList<String> classes = new ArrayList<String>(2);
		 classes.add("positive");
		 classes.add("negative");
		 Attribute classAttr = new Attribute("class", classes);
		 
		 // Declare the feature vector
		 //FastVector featureVector = new FastVector(4);
		 ArrayList<Attribute> featureVector = new ArrayList<Attribute>(4);
		// featureVector.add(attr1);
		// featureVector.add(attr2);
		 featureVector.add(attr3);
		 featureVector.add(classAttr);
		 
		 // Create an empty training set
		 Instances trainingSet = new Instances("Rel", featureVector, 10);
		 // Set class index
		 trainingSet.setClassIndex(1);
		 Instance inst = new DenseInstance(2);
		 //inst.setValue(featureVector.get(0), 29);
		// inst.setValue(featureVector.get(1), 6000);
		 inst.setValue(featureVector.get(0), "gray");
		 inst.setValue(featureVector.get(1), "positive");
		 // add the instance
		 trainingSet.add(inst);
		 
		 Apriori apriori = new Apriori();
		 apriori.buildAssociations(trainingSet);
		 System.out.println("-------------apriori-------------");
		 System.out.println(apriori);
		 System.out.println("-------------apriori-------------");
		 
		 
	}

}
