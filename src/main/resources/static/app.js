const API = "/api/transactions";

document.getElementById("form").addEventListener("submit", async (e) => {
    e.preventDefault();

    const data = {
        type: document.getElementById("type").value,
        category: document.getElementById("category").value,
        amount: parseFloat(document.getElementById("amount").value),
        date: new Date().toISOString().split("T")[0]
    };

    await fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });

    document.getElementById("form").reset();
    loadData();
});

async function loadData() {
    const res = await fetch(API);
    const data = await res.json();

    let rows = "";

    data.forEach(t => {
        const color = t.type === "EXPENSE" ? "table-danger" : "table-success";

        rows += `
            <tr class="${color}">
                <td>${t.id}</td>
                <td>${t.type}</td>
                <td>${t.category}</td>
                <td>₹ ${t.amount}</td>
            </tr>
        `;
    });

    document.getElementById("tableBody").innerHTML = rows;
}

async function loadSummary() {
    const res = await fetch(API + "/summary");
    const data = await res.json();

    let formatted = "";
    for (let key in data) {
        formatted += `${key}: ₹ ${data[key]}\n`;
    }

    document.getElementById("summaryBox").innerText = formatted;
}

async function exportData() {
    await fetch(API + "/export");
    alert("Statement exported successfully!");
}

loadData();