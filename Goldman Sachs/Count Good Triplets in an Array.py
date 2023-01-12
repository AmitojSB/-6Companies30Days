from collections import deque 
from typing import List

class FenwickTree:
  def __init__(self, n: int):
    self.sums = [0] * (n + 1)

  def update(self, i: int, delta: int) -> None:
    while i < len(self.sums):
      self.sums[i] += delta
      i += FenwickTree.lowbit(i)

  def get(self, i: int) -> int:
    summ = 0
    while i > 0:
      summ += self.sums[i]
      i -= FenwickTree.lowbit(i)
    return summ

  @staticmethod
  def lowbit(i: int) -> int:
    return i & -i


class Solution:
  def goodTriplets(self, nums1: List[int], nums2: List[int]) -> int:
    n = len(nums1)
    numToIndex = {num: i for i, num in enumerate(nums1)}

    A = [numToIndex[num] for num in nums2]

    leftSmaller = [0] * n

    rightLarger = [0] * n
    tree1 = FenwickTree(n)  
    tree2 = FenwickTree(n)
    for i, a in enumerate(A):
      leftSmaller[i] = tree1.get(a)
      tree1.update(a + 1, 1)

    for i, a in reversed(list(enumerate(A))):
      rightLarger[i] = tree2.get(n) - tree2.get(a)
      tree2.update(a + 1, 1)

    return sum(a * b for a, b in zip(leftSmaller, rightLarger))