import { ScrollArea } from "@radix-ui/react-scroll-area";
import React, { useEffect } from "react";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Badge } from "@/components/ui/badge";
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogHeader,
    DialogTitle,
    DialogClose,
    DialogTrigger,
  } from "@/components/ui/dialog"
import { Button } from "../../components/ui/button";
import { PlusIcon } from "@radix-ui/react-icons";
import InviteUserForm from "../Project/InviteUserForm";
import IssueList from "./IssueList";
import ChatBox from "./ChatBox";
import { fetchProjectById } from "../../Redux/Project/Action";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
  
export const ProjectDetails = () => {
  const dispatch = useDispatch();
  const {id} = useParams()
  const {project} = useSelector(store => store);
  
  useEffect(()=>{
    dispatch(fetchProjectById(id))
  },[id])
    const handleProjectInvitation = () => {

    }
  return (
    <>
      <div className="mt-5 lg:px-10">
        <div className="lg:flex gap-5 justify-between pb-4">
          <ScrollArea className="h-screen lg:w-[69%] pr-2">
            <div className="text-gray-400 pb-10 w-full">
              <h1 className="text-lg font-semibold pb-5">
                {project.projectDetails?.name}
              </h1>

              <div className="space-y-5 pb-10 text-sm">
                <p className="w-full md:max-w-lg lg:max-w-xl">
                  {project.projectDetails?.description}
                </p>

                <div className="flex">
                  <p className="w-36">Project Lead: </p>
                  <p>{project.projectDetails?.owner.fullName}</p>
                </div>

                <div className="flex">
                  <p className="w-36">Members: </p>
                  <div className="flex items-center gap-2">
                    {project.projectDetails?.team.map((item) => (
                      <Avatar className="cursor-pointer" key={item}>
                        <AvatarFallback>{item.fullName[0]}</AvatarFallback>
                      </Avatar>
                    ))}
                  </div>
                  <Dialog>
                    <DialogTrigger>
                        <DialogClose>
                            <Button size="sm" variant="outline" onClick={handleProjectInvitation} className="ml-2">
                                <span>Invite</span>
                                <PlusIcon className="w-3 h-3"/>
                            </Button>
                        </DialogClose>
                    </DialogTrigger>
                    <DialogContent>
                        <DialogHeader>Invite User</DialogHeader>
                        <InviteUserForm/>
                    </DialogContent>
                  </Dialog>
                </div>

                <div className="flex">
                  <p className="w-36">Category: </p>
                  <p>{project.projectDetails?.category}</p>
                </div>

              </div>

              <section>
                <p className="py-5 border-b text-lg -tracking-wider">Tasks</p>
                <div className="lg:flex md:flex gap-3 justify-between py-5">
                  <IssueList status="pending" title="Todo List"/>
                  <IssueList status="in_progress" title="In Progress"/>
                  <IssueList status="done" title="Done"/>

                </div>

              </section>

            </div>
          </ScrollArea>

          <div className="lg:w-[30%] rounded-md sticky right-5 top-10">
            <ChatBox/>
          </div>
        </div>
      </div>
    </>
  );
};

export default ProjectDetails;
