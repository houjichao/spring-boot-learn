package com.hjc.learn.design.mode.composite.demo;

/**
 * 客户端
 *
 * @author houjichao
 */
public class Client {
    public static void main(String[] args) {
        /*
          组装小J的族谱
         */
        PersonBranch personBranch = getPersonInfo();
        showTree(personBranch);
    }

    private static PersonBranch getPersonInfo() {
        //第一代J
        PersonBranch OneJ = new PersonBranch("第一代J", "男", 150);
        //第一代J的三个儿子
        PersonBranch JA = new PersonBranch("JA", "男", 70);
        PersonBranch JB = new PersonBranch("JB", "男", 60);
        PersonBranch JC = new PersonBranch("JC", "男", 50);
        //JA的三个儿子
        PersonBranch JA1 = new PersonBranch("JA1", "男", 40);
        PersonLeaf JA2 = new PersonLeaf("JA2", "男", 30);
        PersonBranch JA3 = new PersonBranch("JA3", "男", 45);
        //JB的两个儿子
        PersonLeaf JB1 = new PersonLeaf("JB1", "男", 40);
        PersonLeaf JB2 = new PersonLeaf("JB2", "男", 30);
        //JC的儿子小J
        PersonLeaf xiao_J = new PersonLeaf("xiao_J", "男", 20);
        //JA1三个儿子
        PersonBranch JA1_1 = new PersonBranch("JA1_1", "男", 18);
        PersonBranch JA1_2 = new PersonBranch("JA1_2", "男", 16);
        PersonBranch JA1_3 = new PersonBranch("JA1_3", "男", 20);
        //JA3三个儿子
        PersonBranch JA3_1 = new PersonBranch("JA3_1", "男", 16);
        PersonBranch JA3_2 = new PersonBranch("JA3_2", "男", 20);
        PersonBranch JA3_3 = new PersonBranch("JA3_3", "男", 18);

        //开始组装树状族谱
        //组装第一代J下的三个儿子
        OneJ.addPerson(JA);
        OneJ.addPerson(JB);
        OneJ.addPerson(JC);
        //组装JA的三个儿子
        JA.addPerson(JA1);
        JA.addPerson(JA2);
        JA.addPerson(JA3);
        //组装JB的两个儿子
        JB.addPerson(JB1);
        JB.addPerson(JB2);
        //组装JC的儿子
        JC.addPerson(xiao_J);
        //组装JA1的三个儿子
        JA1.addPerson(JA1_1);
        JA1.addPerson(JA1_2);
        JA1.addPerson(JA1_3);
        //组装JA3的三个儿子
        JA3.addPerson(JA3_1);
        JA3.addPerson(JA3_2);
        JA3.addPerson(JA3_3);

        return OneJ;
    }

	/**
	 * 通过递归遍历树
	 * @param root
	 */
	private static void showTree(PersonBranch root) {
        System.out.println(root.getPersonInfo());
        for (PersonMode c : root.getPersonModeList()) {
			//叶子节点
            if (c instanceof PersonLeaf) {
                System.out.println(c.getPersonInfo());
            } else {
				//树枝节点
                showTree((PersonBranch) c);
            }
        }
    }
}
