const API_BASE = "http://localhost:8080/qr"; // backend base URL

// ✅ Generate QR Code
async function generateQRCode() {
  const type = document.getElementById("type").value;
  const content = document.getElementById("content").value;
  const validity = document.getElementById("validity").value * 1000; // convert to ms
 

const url = `${API_BASE}/generate?type=${encodeURIComponent(type)}&content=${encodeURIComponent(content)}&fileUrl=${encodeURIComponent(content)}&validMillis=${validity}`;
if(!content){
        alert("Enter the content");
        return;
    }
  try {
    const response = await fetch(url, { method: "POST" });
   if (!response.ok) {
                const errText = await response.text();
                console.error("Failed:", response.status, errText);
                alert("Error generating QR: " + errText);
                return;
            }

    const blob = await response.blob();
    const qrImage = document.getElementById("qrImage");
    qrImage.src = URL.createObjectURL(blob);
    qrImage.style.display = "block";
  } catch (err) {
    alert(err.message);
  }
}

async function scanQRCode() {

    const token=document.getElementById("scanToken");
    const tokenvalue = token.value; 
    const resultDiv = document.getElementById("result");
    const image1 = document.getElementById("image1");

    if (!tokenvalue) {
        alert("Enter the Token");
        return;
    }
    try {
        const response = await fetch(`${API_BASE}/${tokenvalue}`, {
            method: "GET"
        });
        
        const text = await response.text();

        // Reset result area
        resultDiv.innerHTML = "";
        // image1.style.display = "none";
        if (text.startsWith("http://") || text.startsWith("https://")) {
            if (text.match(/\.(jpeg|jpg|png|gif|webp)$/i)) {
                // If it's an image
                image1.src = text;
                image1.style.display = "block";
            } else if (text.match(/\.pdf$/i)) {
                // If it's a PDF
                resultDiv.innerHTML = `<embed src="${text}" type="application/pdf" width="100%" height="500px" />`;
            } else {
                // Normal link
                resultDiv.innerHTML = `<a href="${text}" target="_blank">${text}</a>`;
            }
        } else {
            // Plain text
            resultDiv.innerText = text;
        } 
        token.value="";
        
    } catch (err) {
        alert("Error scanning QR: " + err.message);
    }
}