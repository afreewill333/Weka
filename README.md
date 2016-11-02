# Weka
weka 


http://forums.pentaho.com/archive/index.php/t-62321.html        

Yes. If you are using the development version of Weka (3.5.x), you can call the method getAllTheRules(). This method returns a three (or up to six if the measure selected is not confidence) element array of FastVectors. The first FastVector contains all the antecedent large itemsets (i.e. left-hand sides of the rules) and the second FastVector contains all the consequent large itemsets (i.e. right-hand sides of the rules). The third FastVector contains the confidence scores (wrapped up in Double classes). The remaining three FastVectors (if measure other than confidence is selected) contain lift, leverage and conviction scores (wrapped in Doubles) respectively. The elements of the FastVectors are ordered in terms of support and metric in question.



http://weka.wikispaces.com/Programmatic+Use


How to get associations rules programmatically from Weka Aproiri Object
MAY 28, 2014PIYUSHDANE29
Hey guys,

The major  blocker  that I came across while working with the Weka was how to extract association rules from apriori class. You can prints the results of algorithm easily but how do you get a Java object that could represent the rules. In this post I’ll explain how I managed to get the Rules out of Apriori Class in Weka.

Step 1: Get all the rules.

FastVector[] rules =  GetAllRules();

use the function GetAllRules() to get the array of fastvectors that represent the rule. Now how do they represent rule using this array of FastVectors?

So, I’ll explain it. This function returns an array of 6 fastvertors each number of items equal to the no. of rules generated. To get the rules we would usually need first 3 array.

rules[0] –  FastVertor containg ItemSet(another class in Weka) as its element corresponding to premises for each rule.

rules[1] – FastVertor containg ItemSet(another class in Weka) as its element corresponding to consequence for each rule.

rules[2] – FastVector containg integers as its element corresponding to the confidence value for each rule.

Step 2: Get the premises, consequences and confidence for i’th rule

ItemSet premises = (ItemSet)rules[0].elementAt(i);

ItemSet consequences = (ItemSet)rules[1].elementAt(i);

int confidence = (int) rules[2].elementAt(i);

Step 3: Get the attributes values in premises and consequences

Now Size of ItemSet premises and consequences is equal to the number of Attributes in Instances over which you ran the Apriori algorithm. Now in ItemSet value of an j’th item is an integer k, where k is the index of value in nominal vector corresponding to the j’th attribute. If value of j’th item is -1 then it does not belong to the ItemSet.

Attribute attribute = instances.attribute(j);

int k = premises.itemAt(j)

String valueOfAttribute = attribute.value(k);
