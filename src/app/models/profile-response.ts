export interface ProfileResponse {
  profileId?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  image?: string;
  description?: string;
  phoneNumber?: string;
  dateOfBirth?: Date;
  country?: string;
  currentJob?: string;
  experience?: number;
  degreeOfEducation?: string;
  certificates?: string;
  // Add other fields as needed
}
