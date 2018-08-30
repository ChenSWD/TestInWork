package mtest.heredity;

import java.util.Random;

//�㷨ʵ��
public class Algorithm {
	private int[] Task = { 10, 5, 6, 18, 22, 9 };
	private int[] Nodes = { 2, 3 };

	// ������ʱ�����
	private float[][] timeMatrix = new float[Task.length][Nodes.length];

	// ��Ӧ�Ⱦ���
	private float[] adaptability;
	// ѡ����ʾ���
	private float[] selectionProbability;

	private int chromosomeNum = 0;

	/**
	 * �Ŵ��㷨 �������� @param iteratorNum Ⱦɫ������ @param chromosomeNum
	 */
	public void startComputer(int iteratorNum, int chromosomeNum) {

		this.chromosomeNum = chromosomeNum;
		// Ⱦɫ�����
		int chromosomeMatrix[][] = createGeneration(chromosomeNum);
		for (int i = 0; i < Task.length; i++) {
			for (int j = 0; j < Nodes.length; j++) {
				timeMatrix[i][j] = Task[i] * 1.0f / Nodes[j];
			}
		}
		adaptability = new float[chromosomeNum];
		selectionProbability = new float[chromosomeNum];
		// ��μ����Ż�
		for (int i = 0; i < iteratorNum; i++) {
			// ������һ������Ⱦɫ�����Ӧ��
			calAdaptability(chromosomeMatrix);

			// ������Ȼѡ�����
			 calSelectionProbability(adaptability);

			// ������һ��Ⱦɫ��  δʵ��
			// createGeneration(chromosomeMatrix);
		}
	}

	// ��ʼ����һ��Ⱦɫ��
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

	// ������һ������Ⱦɫ�����Ӧ��
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

	// ������Ȼѡ�����
	private void calSelectionProbability(float[] adaptability) {
		float sum = 0;
		for (int i = 0; i < chromosomeNum; i++) {
			sum += adaptability[i];
		}
		for (int i = 0; i < chromosomeNum; i++) {
			selectionProbability[i] = adaptability[i] / sum;
		}
	}

	// ������һ��Ⱦɫ��
	private void createGeneration(int[][] chromosomeMatrix) {

	}
}
