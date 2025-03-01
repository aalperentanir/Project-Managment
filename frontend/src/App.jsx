import { useEffect, useState } from "react";
import { Button } from "./components/ui/button";
import Home from "./pages/Home/Home";
import Navbar from "./pages/Navbar/Navbar";
import { Route, Routes } from "react-router-dom";
import ProjectDetails from "./pages/ProjectDetails/ProjectDetails";
import IssueDetails from "./pages/Issue/IssueDetails";
import Subscription from "./pages/Subscription/Subscription";
import Auth from "./pages/Auth/Auth";
import { useDispatch, useSelector } from "react-redux";
import { getUserProfile } from "./Redux/Auth/Action";
import { fetchProjects } from "./Redux/Project/Action";
import UpgradeSuccess from "./pages/Subscription/UpgradeSuccess";
import AcceptInvitation from "./pages/Project/AcceptInvitation";

function App() {
  const [count, setCount] = useState(0);
  const dispatch = useDispatch()
  const {auth} = useSelector(store => store)

  useEffect(()=>{
    dispatch(getUserProfile())
    dispatch(fetchProjects({}))
  },[auth.jwt])

  console.log(auth)

  return (
    <>
      {auth.user? (
        <div>
          <Navbar />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/projects/:id" element={<ProjectDetails />} />
            <Route
              path="/projects/:projectId/issue/:issueId"
              element={<IssueDetails />}
            />
            <Route path="/upgrade_plan" element={<Subscription />} />
            <Route path="/upgrade_plan/success" element={<UpgradeSuccess />} />
            <Route path="/invitations" element={<AcceptInvitation />} />
          </Routes>
        </div>
      ) : (
        <Auth />
      )}
    </>
  );
}

export default App;
