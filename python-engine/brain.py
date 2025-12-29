from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()

class LogData(BaseModel):
    content: str

@app.post("/analyze")
def analyze(log: LogData):
    content = log.content.lower()

    if "failed" in content and "root" in content:
        status = "CRITICAL: Brute Force Attempt"
        status = "WARNING: File Integrity Issue"
    elif "failed" in content:
        status = "DANGER: Unauthorized Access"
    else:
        status = "SAFE: Routine Activity"
        
    return {"analysis": status, "content": log.content}