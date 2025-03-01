import React from "react"
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
import { useDispatch } from "react-redux";
import { createIssue } from "../../Redux/Issue/Action";
import { useParams } from "react-router-dom";

export const CreateIssueForm = ({status}) => {
  const {id} = useParams()
  //console.log("id-----------",id)
  const dispatch = useDispatch()
  const form = useForm({
    defaultValues: {
      issue: "",
      description: ""
    },
  });

  const onSubmit = (data) => {

    dispatch(createIssue({
      title:data.issue,
      description:data.description,
      projectID:id,
      status
    }))
    console.log("create issue data", data); // 🔹 `data`yı console'a yazdır
  };
    return(
        <div>
                  <Form {...form}>
                    {" "}
                    {/* 🔹 Form bileşeni için spread operator ile props geçirildi */}
                    <form className="space-y-3" onSubmit={form.handleSubmit(onSubmit)}>
                      <FormField
                        control={form.control}
                        name="issue"
                        render={({ field }) => (
                          <FormItem>
                            <FormControl>
                              <Input
                                {...field}
                                type="text"
                                className="border w-full border-gray-700 py-5 px-5"
                                placeholder="Issue"
                              />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />
            
                      <FormField
                        control={form.control}
                        name="description"
                        render={({ field }) => (
                          <FormItem>
                            <FormControl>
                              <Input
                                {...field}
                                type="text"
                                className="border w-full border-gray-700 py-5 px-5"
                                placeholder="Description"
                              />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />

            
                      <DialogClose>
                          <Button type="submit" className="w-full mt-5">
                            Create Issue
                          </Button>

                      </DialogClose>
                    </form>
                  </Form>
        </div>
    )
}

export default CreateIssueForm