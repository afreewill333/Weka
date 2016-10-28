import java.util.*;
import java.io.*;
import weka.core.*;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.Evaluation;


public class WekaClassifier {

	public static void main(String[] args) throws Exception{
		// Declare two numeric attributes
		 Attribute attr1 = new Attribute("age");
		 Attribute attr2 = new Attribute("salary");
		 
		 // Declare a nominal attribute along with its values
		 FastVector nominals = new FastVector(3);
		 nominals.addElement("blue");
		 nominals.addElement("gray");
		 nominals.addElement("black");
		 Attribute attr3 = new Attribute("color", nominals);
		 
		 // Declare the class attribute along with its values
		 FastVector classes = new FastVector(2);
		 classes.addElement("positive");
		 classes.addElement("negative");
		 Attribute classAttr = new Attribute("class", classes);
		 
		 // Declare the feature vector
		 //FastVector featureVector = new FastVector(4);
		 ArrayList<Attribute> featureVector = new ArrayList<Attribute>(2);
		 featureVector.add(attr1);
		 featureVector.add(attr2);
		 featureVector.add(attr3);
		 featureVector.add(classAttr);
		 
		 // Create an empty training set
		 Instances trainingSet = new Instances("Rel", featureVector, 10);
		 // Set class index
		 trainingSet.setClassIndex(3);
		 Instance inst = new DenseInstance(4);
		 inst.setValue((Attribute)featureVector.get(0), 29);
		 inst.setValue((Attribute)featureVector.get(1), 6000);
		 inst.setValue((Attribute)featureVector.get(2), "gray");
		 inst.setValue((Attribute)featureVector.get(3), "positive");
		 // add the instance
		 trainingSet.add(inst);
		 
		 // Create a naïve bayes classifier
		 Classifier classifier = (Classifier)new NaiveBayes();
		 classifier.buildClassifier(trainingSet);
		 
		 // Test the model
		 Evaluation eTest = new Evaluation(trainingSet);
		 eTest.evaluateModel(classifier, trainingSet);
		 String strSummary = eTest.toSummaryString();
		 System.out.println(strSummary);		 
		 // Get the confusion matrix
		 double[][] cmMatrix = eTest.confusionMatrix();
		 
		 Instance datarow = new DenseInstance(4);
		 datarow.setValue((Attribute)featureVector.get(0), 29);
		 datarow.setValue((Attribute)featureVector.get(1), 6000);
		 datarow.setValue((Attribute)featureVector.get(2), "gray");
		 datarow.setValue((Attribute)featureVector.get(3), "positive");
		 // Specify that the instance belong to the training set
		 // in order to inherit from the set description
		 datarow.setDataset(trainingSet);		 
		 // Get the likelihood of each classes
		 // fDistribution[0] is the probability of being "positive"
		 // fDistribution[1] is the probability of being "negative"
		 double[] resultDistribution = classifier.distributionForInstance(datarow);
		 for(double d :resultDistribution)System.out.print(d+"\t");System.out.println();

	}

}
