package mtest.heredity;

import java.util.Random;

//算法实现
public class Algorithm {
	private int[] Task = { 10, 5, 6, 18, 22, 9 };
	private int[] Nodes = { 2, 3 };

	// 任务处理时间矩阵
	private float[][] timeMatrix = new float[Task.length][Nodes.length];

	// 适应度矩阵
	private float[] adaptability;
	// 选择概率矩阵
	private float[] selectionProbability;

	private int chromosomeNum = 0;

	/**
	 * 遗传算法 迭代次数 @param iteratorNum 染色体数量 @param chromosomeNum
	 */
	public void startComputer(int iteratorNum, int chromosomeNum) {

		this.chromosomeNum = chromosomeNum;
		// 染色体矩阵
		int chromosomeMatrix[][] = createGeneration(chromosomeNum);
		for (int i = 0; i < Task.length; i++) {
			for (int j = 0; j < Nodes.length; j++) {
				timeMatrix[i][j] = Task[i] * 1.0f / Nodes[j];
			}
		}
		adaptability = new float[chromosomeNum];
		selectionProbability = new float[chromosomeNum];
		// 多次计算优化
		for (int i = 0; i < iteratorNum; i++) {
			// 计算上一代各条染色体的适应度
			calAdaptability(chromosomeMatrix);

			// 计算自然选择概率
			 calSelectionProbability(adaptability);

			// 生成新一代染色体  未实现
			// createGeneration(chromosomeMatrix);
		}
	}

	// 初始化第一代染色体
	private int[][] createGeneration(int chromosomeNum) {
		Random random = new Random();
		int[][] chromosome = new int[chromosomeNum][Task.length];
		for (int i = 0; i < chromosomeNum; i++) {
			for (int j = 0; j < Task.length; j++) {
				chromosome[i][j] = random.nextInt(Nodes.length);
//				System.out.println("i = " + i + " j = " + j + "chromosome[i][j] = " + chromosome[i][j]);
			}
		}
		return chromosome;
	}

	// 计算上一代各条染色体的适应度
	private void calAdaptability(int[][] chromosomeMatrix) {
		for (int num = 0; num < chromosomeNum; num++) {
			float maxSum = Integer.MAX_VALUE;
			for (int nodeIndex = 0; nodeIndex < Nodes.length; nodeIndex++) {
				float sum = 0;
				for (int j = 0; j < Task.length; j++) {
					if (nodeIndex == chromosomeMatrix[num][j]) {
						sum += timeMatrix[j][nodeIndex];
						// sum += Nodes[chromosomeMatrix[num][j]];
					}
				}
//				System.out.println("nodeIndex = "+nodeIndex+" sum = "+sum);
				if (maxSum > sum) {
					maxSum = sum;
				}
			}
			adaptability[num] = 1 / maxSum;
		}
	}

	// 计算自然选择概率
	private void calSelectionProbability(float[] adaptability) {
		float sum = 0;
		for (int i = 0; i < chromosomeNum; i++) {
			sum += adaptability[i];
		}
		for (int i = 0; i < chromosomeNum; i++) {
			selectionProbability[i] = adaptability[i] / sum;
		}
	}

	// 生成新一代染色体
	private void createGeneration(int[][] chromosomeMatrix) {

	}
}
