import java.util.ArrayList;
import java.util.List;

import weka.associations.Apriori;
import weka.associations.AssociationRule;
import weka.associations.AssociationRules;
import weka.associations.Item;
import weka.associations.ItemSet;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SparseInstance;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;


public class WekaAprioriAdvance {

	public static void main(String[] args) throws Exception{

		/*
		 * Bread Coke Beer Diaper Milk
		 * */
		Attribute bread = new Attribute("bread");
		Attribute cola  = new Attribute("cola");
		Attribute beer  = new Attribute("beer");
		Attribute milk  = new Attribute("milk");
		Attribute eggs  = new Attribute("eggs");
		Attribute diapers= new Attribute("diapers");

		 ArrayList<Attribute> featureVector = new ArrayList<Attribute>(3);
		 featureVector.add(bread);
		 featureVector.add(milk);
		 featureVector.add(diapers);
		 featureVector.add(beer);
		 featureVector.add(eggs);
		 featureVector.add(cola);

		 
		 // Create an empty training set
		 Instances dataset = new Instances("Rel", featureVector, 5);
		 Instance inst = new DenseInstance(6);
		 
		 inst.setValue(featureVector.get(0), 1);
		 inst.setValue(featureVector.get(1), 1);
		 inst.setValue(featureVector.get(2), 0);
		 inst.setValue(featureVector.get(3), 0);
		 inst.setValue(featureVector.get(4), 0);
		 inst.setValue(featureVector.get(5), 0);
		 dataset.add(inst);
		 inst.setValue(featureVector.get(0), 1);
		 inst.setValue(featureVector.get(1), 0);
		 inst.setValue(featureVector.get(2), 1);
		 inst.setValue(featureVector.get(3), 1);
		 inst.setValue(featureVector.get(4), 1);
		 inst.setValue(featureVector.get(5), 0);
		 dataset.add(inst);
		 inst.setValue(featureVector.get(0), 0);
		 inst.setValue(featureVector.get(1), 1);
		 inst.setValue(featureVector.get(2), 1);
		 inst.setValue(featureVector.get(3), 1);
		 inst.setValue(featureVector.get(4), 0);
		 inst.setValue(featureVector.get(5), 1);
		 dataset.add(inst);
		 inst.setValue(featureVector.get(0), 1);
		 inst.setValue(featureVector.get(1), 1);
		 inst.setValue(featureVector.get(2), 1);
		 inst.setValue(featureVector.get(3), 1);
		 inst.setValue(featureVector.get(4), 0);
		 inst.setValue(featureVector.get(5), 0);
		 dataset.add(inst);
		 inst.setValue(featureVector.get(0), 1);
		 inst.setValue(featureVector.get(1), 1);
		 inst.setValue(featureVector.get(2), 1);
		 inst.setValue(featureVector.get(3), 0);
		 inst.setValue(featureVector.get(4), 0);
		 inst.setValue(featureVector.get(5), 1);
		 dataset.add(inst);
		 
		 Discretize discretize = new Discretize();
		 //String[] options = new String[2];
		 //options[0]="-B"; options[1]="2";
		 //discretize.setOptions(options);
		 discretize.setInputFormat(dataset);
		 dataset=Filter.useFilter(dataset,discretize);
		 
		 Apriori apriori = new Apriori();
		 String[] options = new String[4];
		 options[0]="-C";options[1]="0.5";
		 options[2]="-M";options[3]="0.1";
		 apriori.setOptions(options);
		 //apriori.setOutputItemSets(true);
		 apriori.buildAssociations(dataset);
		 
		 		 

		 /*-----Bread-----Milk-----Diapers-----Beer-----Eggs-----Cola-----*/
		 /*-------1---------1--------0-----------0--------0--------0------*/
		 /*-------1---------0--------1-----------1--------1--------0------*/
		 /*-------0---------1--------1-----------1--------0--------1------*/
		 /*-------1---------1--------1-----------1--------0--------0------*/
		 /*-------1---------1--------1-----------0--------0--------1------*/
		 AssociationRules ars=apriori.getAssociationRules();
		 for(AssociationRule ar : ars.getRules()){
			 System.out.println("PremiseSupport:"+ar.getPremiseSupport());/* bad choice */
			 
			 for(Item i:ar.getPremise()){
				 //System.out.print(i.getAttribute().index());/* important!  index of wordvec*/
				 //System.out.println(i.getAttribute().getRevision());
				 System.out.print(i.getAttribute().name()+" : ");/* important! name of feature namely:word */
				 //System.out.println(i.getAttribute().numValues());
				 //System.out.println(i.getAttribute().getClass());
				 ////System.out.print(i.getClass()+"\t"+i.getAttribute());
				 //System.out.println(i.toString());
				 //System.out.println(i.getFrequency());
				 System.out.print(i.getItemValueAsString()+"\t");
			 }
			 System.out.println("\n------------END------------");
		 }


//		 System.out.println("-------------apriori-------------");
		 System.out.println(apriori.toString());
//		 System.out.println("-------------apriori-------------");
		 
		 List[] rules = apriori.getAllTheRules();
		 ItemSet premises= (ItemSet)rules[0].get(0);
		 ItemSet consequences = (ItemSet)rules[1].get(0);
		 Double confidence = (Double)rules[2].get(0);
		 
		 ItemSet itemset = (ItemSet)rules[0].get(1);
		 for(int idx : itemset.items()){
			 System.out.print(idx+"\t");
		 }

		 
	}

}
