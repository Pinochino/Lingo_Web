export const selectMergedTests = (state) => {
  const { tests } = state.tests;
  const { attempts = [] } = state.attempts;

  console.log(attempts);


  return tests.map(test => {
    const found = attempts.find(a => a.quizId === test.id);
    return found ? { ...test, finish: true } : test;
  });
};
