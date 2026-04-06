const state = {
    alumni: [],
    jobs: [],
    connections: []
};

const apiBaseUrl = window.location.protocol.startsWith("http")
    ? window.location.origin
    : "http://localhost:8080";

const alumniForm = document.getElementById("alumniForm");
const jobForm = document.getElementById("jobForm");
const connectionForm = document.getElementById("connectionForm");
const searchInput = document.getElementById("searchInput");
const refreshButton = document.getElementById("refreshButton");

const alumniList = document.getElementById("alumniList");
const jobList = document.getElementById("jobList");
const connectionList = document.getElementById("connectionList");

const alumniCount = document.getElementById("alumniCount");
const jobCount = document.getElementById("jobCount");
const connectionCount = document.getElementById("connectionCount");

async function request(url, options = {}) {
    const response = await fetch(`${apiBaseUrl}${url}`, {
        headers: {
            "Content-Type": "application/json"
        },
        ...options
    });

    if (!response.ok) {
        let message = "Request failed";
        try {
            const errorBody = await response.json();
            message = errorBody.message || errorBody.error || JSON.stringify(errorBody);
        } catch (error) {
            message = await response.text() || message;
        }
        throw new Error(message);
    }

    if (response.status === 204) {
        return null;
    }

    return response.json();
}

function setStatus(elementId, message, type) {
    const element = document.getElementById(elementId);
    element.textContent = message;
    element.className = `status-message ${type}`;
}

function clearStatus(elementId) {
    const element = document.getElementById(elementId);
    element.textContent = "";
    element.className = "status-message";
}

function createEmptyState(message) {
    const box = document.createElement("div");
    box.className = "empty-state";
    box.textContent = message;
    return box;
}

function renderAlumni() {
    const query = searchInput.value.trim().toLowerCase();
    const filtered = state.alumni.filter((alumni) => {
        const searchable = [
            alumni.name,
            alumni.company,
            alumni.skills,
            String(alumni.graduationYear)
        ].join(" ").toLowerCase();
        return searchable.includes(query);
    });

    alumniList.innerHTML = "";
    if (!filtered.length) {
        alumniList.appendChild(createEmptyState("No alumni match the current filter."));
        return;
    }

    filtered.forEach((alumni) => {
        const card = document.createElement("article");
        card.className = "data-card alumni-card";
        card.innerHTML = `
            <div class="card-top">
                <h3>${alumni.name || "Unnamed Alumni"}</h3>
                <span class="card-badge orange">ID ${alumni.id ?? "-"}</span>
            </div>
            <div class="card-meta">
                <div class="meta-row"><span class="meta-label">Email</span><span>${alumni.email || "-"}</span></div>
                <div class="meta-row"><span class="meta-label">Company</span><span class="meta-chip">${alumni.company || "Not added"}</span></div>
                <div class="meta-row"><span class="meta-label">Skills</span><span>${alumni.skills || "Not added"}</span></div>
                <div class="meta-row"><span class="meta-label">Batch</span><span>${alumni.graduationYear || "-"}</span></div>
            </div>
        `;
        alumniList.appendChild(card);
    });
}

function renderJobs() {
    jobList.innerHTML = "";
    if (!state.jobs.length) {
        jobList.appendChild(createEmptyState("No jobs posted yet."));
        return;
    }

    state.jobs.forEach((job) => {
        const card = document.createElement("article");
        card.className = "data-card job-card";
        card.innerHTML = `
            <div class="card-top">
                <h3>${job.title || "Untitled Job"}</h3>
                <span class="card-badge pink">Open Role</span>
            </div>
            <div class="card-meta">
                <div class="meta-row"><span class="meta-label">Company</span><span class="meta-chip">${job.company || "-"}</span></div>
                <div class="meta-row"><span class="meta-label">Description</span><span>${job.description || "-"}</span></div>
                <div class="meta-row"><span class="meta-label">Posted By</span><span>${job.postedBy?.name || `Alumni ID ${job.postedBy?.id ?? "-"}`}</span></div>
            </div>
        `;
        jobList.appendChild(card);
    });
}

function renderConnections() {
    connectionList.innerHTML = "";
    if (!state.connections.length) {
        connectionList.appendChild(createEmptyState("No connections created yet."));
        return;
    }

    state.connections.forEach((connection) => {
        const card = document.createElement("article");
        card.className = "data-card connection-card";
        card.innerHTML = `
            <div class="card-top">
                <h3>New Connection</h3>
                <span class="card-badge blue">Linked</span>
            </div>
            <div class="card-meta">
                <div class="connection-link">${connection.alumni1?.name || `Alumni ${connection.alumni1?.id ?? "-"}`} to ${connection.alumni2?.name || `Alumni ${connection.alumni2?.id ?? "-"}`}</div>
                <div class="meta-row"><span class="meta-label">Alumni 1</span><span>ID ${connection.alumni1?.id ?? "-"}</span></div>
                <div class="meta-row"><span class="meta-label">Alumni 2</span><span>ID ${connection.alumni2?.id ?? "-"}</span></div>
            </div>
        `;
        connectionList.appendChild(card);
    });
}

function updateCounts() {
    alumniCount.textContent = state.alumni.length;
    jobCount.textContent = state.jobs.length;
    connectionCount.textContent = state.connections.length;
}

async function loadDashboardData() {
    const [alumni, jobs, connections] = await Promise.all([
        request("/api/alumni/all"),
        request("/api/jobs"),
        request("/api/connections")
    ]);

    state.alumni = alumni;
    state.jobs = jobs;
    state.connections = connections;

    updateCounts();
    renderAlumni();
    renderJobs();
    renderConnections();
}

alumniForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    clearStatus("alumniStatus");

    const formData = new FormData(alumniForm);
    const payload = {
        name: formData.get("name"),
        email: formData.get("email"),
        password: formData.get("password"),
        company: formData.get("company"),
        skills: formData.get("skills"),
        graduationYear: Number(formData.get("graduationYear"))
    };

    try {
        await request("/api/alumni/register", {
            method: "POST",
            body: JSON.stringify(payload)
        });
        alumniForm.reset();
        setStatus("alumniStatus", "Alumni registered successfully.", "success");
        await loadDashboardData();
    } catch (error) {
        setStatus("alumniStatus", error.message, "error");
    }
});

jobForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    clearStatus("jobStatus");

    const formData = new FormData(jobForm);
    const payload = {
        title: formData.get("title"),
        company: formData.get("company"),
        description: formData.get("description"),
        postedBy: {
            id: Number(formData.get("postedById"))
        }
    };

    try {
        await request("/api/jobs", {
            method: "POST",
            body: JSON.stringify(payload)
        });
        jobForm.reset();
        setStatus("jobStatus", "Job posted successfully.", "success");
        await loadDashboardData();
    } catch (error) {
        setStatus("jobStatus", error.message, "error");
    }
});

connectionForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    clearStatus("connectionStatus");

    const formData = new FormData(connectionForm);
    const alumni1Id = Number(formData.get("alumni1Id"));
    const alumni2Id = Number(formData.get("alumni2Id"));

    if (alumni1Id === alumni2Id) {
        setStatus("connectionStatus", "Choose two different alumni IDs.", "error");
        return;
    }

    const payload = {
        alumni1: { id: alumni1Id },
        alumni2: { id: alumni2Id }
    };

    try {
        await request("/api/connections", {
            method: "POST",
            body: JSON.stringify(payload)
        });
        connectionForm.reset();
        setStatus("connectionStatus", "Connection created successfully.", "success");
        await loadDashboardData();
    } catch (error) {
        setStatus("connectionStatus", error.message, "error");
    }
});

searchInput.addEventListener("input", renderAlumni);
refreshButton.addEventListener("click", loadDashboardData);

document.querySelectorAll("[data-scroll-target]").forEach((button) => {
    button.addEventListener("click", () => {
        const target = document.querySelector(button.dataset.scrollTarget);
        target?.scrollIntoView({ behavior: "smooth", block: "start" });
    });
});

loadDashboardData().catch((error) => {
    setStatus("alumniStatus", `Unable to load backend data: ${error.message}`, "error");
});
