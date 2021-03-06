import java.util.*;
import java.io.*;

import weka.associations.Apriori;
import weka.associations.AprioriItemSet;
import weka.associations.AssociationRule;
import weka.associations.AssociationRules;
import weka.associations.Item;
import weka.core.*;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.Evaluation;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;


public class WekaApriori {

	public static void main(String[] args) throws Exception{

		Attribute age = new Attribute("age");
		 // Declare a nominal attribute along with its values
		 ArrayList<String> nominals = new ArrayList<String>(3);
		 nominals.add("blue");
		 nominals.add("gray");
		 nominals.add("black");
		 Attribute attr = new Attribute("color", nominals);
		 
		 // Declare the class attribute along with its values
		 ArrayList<String> classes = new ArrayList<String>(2);
		 classes.add("positive");
		 classes.add("negative");
		 Attribute classAttr = new Attribute("class", classes);
		 
		 // Declare the feature vector
		 //FastVector featureVector = new FastVector(4);
		 ArrayList<Attribute> featureVector = new ArrayList<Attribute>(3);
		 featureVector.add(age);
		 featureVector.add(attr);
		 featureVector.add(classAttr);
		 
		 // Create an empty training set
		 Instances trainingSet = new Instances("Rel", featureVector, 10);
		 // Set class index
		 trainingSet.setClassIndex(2);
		 
		 Instance inst = new DenseInstance(3);
		 inst.setValue(featureVector.get(0), 29);
		 inst.setValue(featureVector.get(1), "gray");
		 inst.setValue(featureVector.get(2), "positive");
		 // add the instance
		 trainingSet.add(inst);
		 inst.setValue(featureVector.get(0), 29);
		 inst.setValue(featureVector.get(1), "gray");
		 inst.setValue(featureVector.get(2), "positive");
		 trainingSet.add(inst);
		 inst.setValue(featureVector.get(0), 19);
		 inst.setValue(featureVector.get(1), "gray");
		 inst.setValue(featureVector.get(2), "positive");
		 trainingSet.add(inst);
		 /*-----age---------color---------class-----*/
		 /*-----29 ---------gray ---------posit-----*/
		 /*-----29 ---------gray ---------posit-----*/
		 /*-----19 ---------gray ---------posit-----*/
		 
		 Discretize discretize = new Discretize();
		 //String[] options = new String[4];
		 //options[0]="-B"; options[1]="3";
		 //options[2]="-R"; options[3]="0";
		 //discretize.setOptions(options);
		 discretize.setInputFormat(trainingSet);//dataset
		 trainingSet=Filter.useFilter(trainingSet,discretize);
		 
		 Apriori apriori = new Apriori();
		 apriori.setLowerBoundMinSupport(1);
		 apriori.setUpperBoundMinSupport(2);
		 apriori.buildAssociations(trainingSet);
		 		 
		 AssociationRules ars=apriori.getAssociationRules();
		 for(AssociationRule ar:ars.getRules()){
			 //System.out.println("Support:"+ar.getPremiseSupport());/* bad choice */
			 System.out.println("Support:"+ar.getConsequenceSupport());
			 
			 for(Item i:ar.getConsequence()){
				 //System.out.print(i.getAttribute().index());/* important!  index of wordvec*/
				 //System.out.println(i.getAttribute().getRevision());
				 System.out.print(i.getAttribute().name()+"#");/* important! name of feature namely:word */
				 //System.out.println(i.getAttribute().numValues());
				 //System.out.println(i.getAttribute().getClass());
				 ////System.out.print(i.getClass()+"\t"+i.getAttribute());
				 //System.out.println(i.toString());
				 //System.out.println(i.getFrequency());
				 System.out.print(i.getItemValueAsString()+"\t");
			 }
			 System.out.println("\n------------END------------");
		 }

//
//		 System.out.println("-------------apriori-------------");
//		 System.out.println(apriori);
//		 System.out.println("-------------apriori-------------");
		 
		 
	}

}
