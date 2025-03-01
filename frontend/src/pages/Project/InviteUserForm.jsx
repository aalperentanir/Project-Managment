import React from "react";
import { useForm } from "react-hook-form";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
  DialogClose,
} from "@/components/ui/dialog";
import { Button } from "../../components/ui/button";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { Cross1Icon } from "@radix-ui/react-icons";
import { useDispatch } from "react-redux";
import { inviteToProject } from "../../Redux/Project/Action";
import { useParams } from "react-router-dom";

export const InviteUserForm = () => {
  const dispatch = useDispatch()
  const {id} = useParams()
  const form = useForm({
    defaultValues: {
      email: "",
    },
  });

  const onSubmit = (data) => {
    dispatch(inviteToProject({email:data.email, projectId:id}))
    console.log("create project data", data); // 🔹 `data`yı console'a yazdır
  };
  return (
    <div>
      <Form {...form}>
        {" "}
        {/* 🔹 Form bileşeni için spread operator ile props geçirildi */}
        <form className="space-y-3" onSubmit={form.handleSubmit(onSubmit)}>
          <FormField
            control={form.control}
            name="email"
            render={({ field }) => (
              <FormItem>
                <FormControl>
                  <Input
                    {...field}
                    type="text"
                    className="border w-full border-gray-700 py-5 px-5"
                    placeholder="User email address"
                  />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <DialogClose>
              <Button type="submit" className="w-full mt-5">
                Invite
              </Button>
          </DialogClose>
        </form>
      </Form>
    </div>
  );
};

export default InviteUserForm;
