import { User } from "../user/User";

export interface Session{
  user : User; 
  token : string; 
}