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
		 
		 Discretize discretize = new Discretize();
		 //String[] options = new String[4];
		 //options[0]="-B"; options[1]="3";
		 //options[2]="-R"; options[3]="0";
		 //discretize.setOptions(options);
		 discretize.setInputFormat(trainingSet);//dataset
		 trainingSet=Filter.useFilter(trainingSet,discretize);
		 
		 Apriori apriori = new Apriori();
		 apriori.buildAssociations(trainingSet);
		 
		 AprioriItemSet set = new AprioriItemSet(0);
		 
		 List[] listArray = apriori.getAllTheRules();
		 AssociationRules ars=apriori.getAssociationRules();
		 for(AssociationRule ar:ars.getRules()){
			 for(Item i:ar.getPremise()){
				 System.out.println(i.getAttribute().index());/* important!  index of wordvec*/
				 System.out.println(i.getAttribute().getRevision());
				 System.out.println(i.getAttribute().name());/* important! name of feature namely:word */
				 System.out.println(i.getAttribute().numValues());
				 System.out.println(i.getAttribute().getClass());
				 //System.out.print(i.getClass()+"\t"+i.getAttribute());
				 System.out.println(i.toString());
				 System.out.println(i.getComparisonAsString());
				 System.out.println(i.getFrequency());
				 System.out.println(i.getClass());
				 System.out.println(i.getItemValueAsString());
			 }
			 System.out.println("------END");
		 }
		 
//		 for(List list: listArray){
//			 for(Object o: list){
//				 System.out.print(o+"\t");
//			 }
//			 System.out.println();
//		 }
//
//		 System.out.println("-------------apriori-------------");
//		 System.out.println(apriori);
//		 System.out.println("-------------apriori-------------");
		 
		 
	}

}