function submit() {
  const id = document.querySelector("#inputId").value;

  // apiのエンドポイントをこんなふうにベタガキしたくないね
  getData("/post/" + id);
}

async function getData(url) {
  const data = await fetch(url).then((res) => res.json());

  document.querySelector("#title").textContent = data.title;
  document.querySelector("#body").textContent = data.body;
}
