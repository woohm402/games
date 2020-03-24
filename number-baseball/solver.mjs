import readline from "readline";

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const isNoDuplicate = (num) =>
  ![...num].some((n1, i1) => [...num].some((n2, i2) => n1 === n2 && i1 !== i2));

const prompt = (question) =>
  new Promise((resolve) => rl.question(question, resolve));

const isPossible = (q, s, b) => (num) => {
  const strikeCnt = [...num].filter((n, i) => n === q[i]).length;
  const ballCnt = [...num].filter((n, i) => q.includes(n) && q[i] !== n).length;
  return strikeCnt === s && ballCnt === b;
};

const run = async (length) => {
  let arr = Array.from({ length: Math.pow(10, length) }, (_, i) =>
    `${i}`.padStart(length, "0")
  ).filter(isNoDuplicate);

  while (arr.length > 1) {
    const q = arr[Math.floor(Math.random() * arr.length)];
    const [s, b] = (await prompt(`is it ${q}? : `)).split(" ").map(Number);
    arr = arr.filter(isPossible(q, s, b));
  }

  if (arr.length === 1) console.log(`it is ${arr[0]}`);
  else console.log("you lied");
};

const len = Number(await prompt("length of number: "));

await run(len);
process.exit(0);
